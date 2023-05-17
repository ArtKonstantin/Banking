package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.exception.CreditServiceBadRequestException;
import org.aston.credit.exception.CreditServiceConflictException;
import org.aston.credit.exception.CreditServiceNotFoundException;
import org.aston.credit.exception.ForbiddenException;
import org.aston.credit.repository.CreditOrderRepository;
import org.aston.credit.repository.CreditProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.aston.credit.entity.enums.OrderStatusEnum.APPROVED_BY_BANK;
import static org.aston.credit.entity.enums.OrderStatusEnum.APPROVED_BY_CLIENT;
import static org.aston.credit.entity.enums.OrderStatusEnum.PENDING;
import static org.aston.credit.entity.enums.OrderStatusEnum.REJECTED_BY_CLIENT;
import static org.aston.credit.exception.EnumCodeAndCommentException.AMOUNT_SHOULD_BE_WITHIN_MIN_AND_MAX;
import static org.aston.credit.exception.EnumCodeAndCommentException.APPLICATION_ID_NOT_FOUND;
import static org.aston.credit.exception.EnumCodeAndCommentException.CREDIT_PRODUCT_NOT_ACTIVE;
import static org.aston.credit.exception.EnumCodeAndCommentException.CREDIT_PRODUCT_NOT_FOUND;
import static org.aston.credit.exception.EnumCodeAndCommentException.INVALID_STATUS_ORDER;
import static org.aston.credit.exception.EnumCodeAndCommentException.PERIOD_SHOULD_BE_WITHIN_MIN_AND_MAX;
import static org.aston.credit.exception.EnumCodeAndCommentException.STATUS_ALREADY_SET;

@Service
@RequiredArgsConstructor
public class CreditOrderService {
    private final CreditOrderRepository creditOrderRepository;
    private final CreditProductRepository creditProductRepository;

    public void create(UUID clientId, CreditOrderEntity creditOrder) {
        final BigDecimal amount = creditOrder.getAmount();
        final int periodMonths = creditOrder.getPeriodMonths();
        final Optional<CreditProductEntity> creditProductOptional = creditProductRepository.
                findById(creditOrder.getCreditProduct().getId());
        String generatedOrderNumber;

        if (creditProductOptional.isEmpty()) {
            throw new CreditServiceNotFoundException(CREDIT_PRODUCT_NOT_FOUND);
        }

        CreditProductEntity creditProduct = creditProductOptional.get();

        if (!creditProduct.isProductIsActive()) {
            throw new CreditServiceNotFoundException(CREDIT_PRODUCT_NOT_ACTIVE);
        }

        if (amount.compareTo(creditProduct.getMinSum()) == -1
                || amount.compareTo(creditProduct.getMaxSum()) == 1) {
            throw new CreditServiceBadRequestException(AMOUNT_SHOULD_BE_WITHIN_MIN_AND_MAX);
        }

        if (periodMonths < creditProduct.getMinPeriodMonths()
                || periodMonths > creditProduct.getMaxPeriodMonths()) {
            throw new CreditServiceBadRequestException(PERIOD_SHOULD_BE_WITHIN_MIN_AND_MAX);
        }

        do {
            generatedOrderNumber = RandomStringUtils.randomNumeric(7);
        }
        while (creditOrderRepository.findByNumber(generatedOrderNumber) != null);

        creditOrder.setNumber(generatedOrderNumber);
        creditOrder.setClientId(clientId);
        creditOrder.setStatus(PENDING);
        creditOrder.setCreationDate(LocalDate.now());
        creditOrderRepository.save(creditOrder);
    }

    public List<CreditOrderEntity> getCreditOrdersByClientId(UUID clientId) {

        return creditOrderRepository.findAllByClientId(clientId);
    }

    public void recall(UUID clientId, long applicationId) {

        final CreditOrderEntity creditOrder = getCreditOrder(applicationId);

        if (!creditOrder.getClientId().equals(clientId)) {
            throw new ForbiddenException();
        }

        if (creditOrder.getStatus() == REJECTED_BY_CLIENT) {
            throw new CreditServiceConflictException(STATUS_ALREADY_SET);
        }

        if (creditOrder.getStatus() != PENDING &&
                creditOrder.getStatus() != APPROVED_BY_BANK) {
            throw new CreditServiceBadRequestException(INVALID_STATUS_ORDER);
        }

        creditOrder.setStatus(REJECTED_BY_CLIENT);
        creditOrderRepository.save(creditOrder);
    }

    public void confirmation(UUID clientId, long applicationId) {

        final CreditOrderEntity creditOrder = getCreditOrder(applicationId);

        if (!creditOrder.getClientId().equals(clientId)) {
            throw new ForbiddenException();
        }

        if (creditOrder.getStatus() == APPROVED_BY_CLIENT) {
            throw new CreditServiceConflictException(STATUS_ALREADY_SET);
        }

        if (creditOrder.getStatus() != APPROVED_BY_BANK) {
            throw new CreditServiceBadRequestException(INVALID_STATUS_ORDER);
        }

        creditOrder.setStatus(APPROVED_BY_CLIENT);
        creditOrderRepository.save(creditOrder);
    }

    private CreditOrderEntity getCreditOrder(long applicationId) {
        final Optional<CreditOrderEntity> creditOrderOptional = creditOrderRepository.findById(applicationId);

        if (creditOrderOptional.isEmpty()) {
            throw new CreditServiceNotFoundException(APPLICATION_ID_NOT_FOUND);
        }

        return creditOrderOptional.get();
    }
}
