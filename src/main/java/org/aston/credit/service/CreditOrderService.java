package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditOrderResponseDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.OrderStatusEnum;
import org.aston.credit.mapper.CreditOrderMapper;
import org.aston.credit.repository.CreditOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditOrderService {
    private final CreditOrderRepository creditOrderRepository;
    private final CreditOrderMapper creditOrderMapper;

    public List<CreditOrderEntity> getAll() {
        return creditOrderRepository.findAll();
    }

    public void create(UUID clientId, CreditOrderEntity creditOrder) {
        creditOrder.setClientId(clientId);
        creditOrder.setStatus(OrderStatusEnum.PENDING);
        creditOrderRepository.save(creditOrder);
    }

    public List<CreditOrderResponseDto> getCreditOrdersByClientId(UUID clientId) {
        List<CreditOrderEntity> creditOrders = creditOrderRepository.findAllByClientId(clientId);
        return creditOrderMapper.toDtoList(creditOrders);
    }

    public void reject(UUID clientId, CreditOrderEntity order) {
        final CreditOrderEntity creditOrder = creditOrderRepository.getReferenceById(order.getId());
        if(creditOrder.getClientId().equals(clientId)) {
            if(creditOrder.getStatus().equals(order.getStatus())) {
                creditOrder.setStatus(OrderStatusEnum.REJECT_BY_CLIENT);
                creditOrderRepository.save(creditOrder);
            }
        }
    }

    public void approved(UUID clientId, CreditOrderEntity order) {
        final CreditOrderEntity creditOrder = creditOrderRepository.getReferenceById(order.getId());
        if(creditOrder.getClientId().equals(clientId)) {
            if(creditOrder.getStatus().equals(order.getStatus())) {
                creditOrder.setStatus(OrderStatusEnum.APPROVED_BY_CLIENT);
                creditOrderRepository.save(creditOrder);
            }
        }
    }
}
