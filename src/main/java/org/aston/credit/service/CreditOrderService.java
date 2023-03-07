package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.entity.OrderStatusEnum;
import org.aston.credit.exception.BadRequestException;
import org.aston.credit.exception.ForbiddenException;
import org.aston.credit.repository.CreditOrderRepository;
import org.aston.credit.repository.CreditProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.aston.credit.entity.OrderStatusEnum.APPROVED_BY_BANK;
import static org.aston.credit.entity.OrderStatusEnum.APPROVED_BY_CLIENT;
import static org.aston.credit.entity.OrderStatusEnum.PENDING;
import static org.aston.credit.entity.OrderStatusEnum.REJECT_BY_CLIENT;

@Service
@RequiredArgsConstructor
public class CreditOrderService {
    private final CreditOrderRepository creditOrderRepository;
    private final CreditProductRepository creditProductRepository;

    public List<CreditOrderEntity> getCreditOrdersByClientId(UUID clientId) {
        List<CreditOrderEntity> creditOrders = creditOrderRepository.findAllByClientId(clientId);

        if (creditOrders.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return creditOrders;
    }

    public void create(UUID clientId, CreditOrderEntity creditOrder) {
        final BigDecimal amount = creditOrder.getAmount();
        final int periodMonths = creditOrder.getPeriodMonths();
        final CreditProductEntity creditProduct = creditProductRepository.
                getReferenceById(creditOrder.getCreditProduct().getId());

        if (!creditProduct.isProductIsActive()) {
            throw new BadRequestException();
        }

        if (amount.compareTo(creditProduct.getMinSum()) == -1
                || amount.compareTo(creditProduct.getMaxSum()) == 1) {
            throw new BadRequestException();
        }

        if (periodMonths < creditProduct.getMinPeriodMonths()
                || periodMonths > creditProduct.getMaxPeriodMonths()) {
            throw new BadRequestException();
        }

        creditOrder.setClientId(clientId);
        creditOrder.setStatus(OrderStatusEnum.PENDING);
        creditOrderRepository.save(creditOrder);
    }

    public void approved(UUID clientId, CreditOrderEntity order) {
        final CreditOrderEntity creditOrder = creditOrderRepository.getReferenceById(order.getId());
        final OrderStatusEnum status = creditOrder.getStatus();
        final OrderStatusEnum requestStatus = order.getStatus();

        if (!creditOrder.getClientId().equals(clientId)) {
            throw new ForbiddenException();
        }

        switch (requestStatus) {
            case REJECT_BY_CLIENT:
                if (!(status.equals(PENDING)
                        || status.equals(APPROVED_BY_BANK)))
                    throw new BadRequestException();

                creditOrder.setStatus(REJECT_BY_CLIENT);
                creditOrderRepository.save(creditOrder);
                break;

            case APPROVED_BY_CLIENT:
                if (!status.equals(APPROVED_BY_BANK))
                    throw new BadRequestException();

                creditOrder.setStatus(APPROVED_BY_CLIENT);
                creditOrderRepository.save(creditOrder);
                break;

            default:
                break;
        }
    }
}
