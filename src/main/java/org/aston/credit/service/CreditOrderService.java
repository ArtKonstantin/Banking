package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditOrderResponseDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.entity.OrderStatusEnum;
import org.aston.credit.exception.BadRequestException;
import org.aston.credit.exception.ForbiddenException;
import org.aston.credit.mapper.CreditOrderMapper;
import org.aston.credit.repository.CreditOrderRepository;
import org.aston.credit.repository.CreditProductRepository;
import org.aston.credit.repository.CreditRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditOrderService {
    private final CreditOrderRepository creditOrderRepository;
    private final CreditOrderMapper creditOrderMapper;
    private final CreditProductRepository creditProductRepository;
    private final CreditRepository creditRepository;

    public List<CreditOrderEntity> getAll() {
        return creditOrderRepository.findAll();
    }

    public void create(UUID clientId, CreditOrderEntity creditOrder) {
        final CreditProductEntity creditProduct = creditProductRepository.
                getReferenceById(creditOrder.getCreditProduct().getId());

        if(creditProduct == null) {
            throw new EntityNotFoundException();
        }

        if(!creditProduct.isProductIsActive()) {
            throw new BadRequestException();
        }

        if(creditOrder.getAmount().compareTo(creditProduct.getMinSum()) == -1
                || creditOrder.getAmount().compareTo(creditProduct.getMaxSum()) == 1) {
            throw new BadRequestException();
        }

        if(creditOrder.getPeriodMonths() < creditProduct.getMinPeriodMonths()
                || creditOrder.getPeriodMonths() > creditProduct.getMaxPeriodMonths()) {
            throw new BadRequestException();
        }

        creditOrder.setClientId(clientId);
        creditOrder.setStatus(OrderStatusEnum.PENDING);
        creditOrderRepository.save(creditOrder);
    }

    public List<CreditOrderResponseDto> getCreditOrdersByClientId(UUID clientId) {
        List<CreditOrderEntity> creditOrders = creditOrderRepository.findAllByClientId(clientId);

        if(creditOrders.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return creditOrderMapper.toDtoList(creditOrders);
    }

    public void reject(UUID clientId, CreditOrderEntity order) {
        final CreditOrderEntity creditOrder = creditOrderRepository.getReferenceById(order.getId());

        if(!creditOrder.getClientId().equals(clientId)) {
            throw new ForbiddenException();
        }

        if(!creditOrder.getStatus().equals(order.getStatus())) {
            throw new BadRequestException();
        }

        creditOrder.setStatus(OrderStatusEnum.REJECT_BY_CLIENT);
        creditOrderRepository.save(creditOrder);
    }

    public void approved(UUID clientId, CreditOrderEntity order) {
        final CreditOrderEntity creditOrder = creditOrderRepository.getReferenceById(order.getId());

        if(!creditOrder.getClientId().equals(clientId)) {
            throw new ForbiddenException();
        }

        if(!creditOrder.getStatus().equals(order.getStatus())) {
            throw new BadRequestException();
        }

        creditOrder.setStatus(OrderStatusEnum.APPROVED_BY_CLIENT);
        creditOrderRepository.save(creditOrder);
    }
}
