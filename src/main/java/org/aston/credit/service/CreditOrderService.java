package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.NewCreditOrderRequestDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.OrderStatusEnum;
import org.aston.credit.repository.CreditOrderRepository;
import org.aston.credit.repository.CreditProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditOrderService {
    private final CreditOrderRepository creditOrderRepository;
    private final CreditProductRepository creditProductRepository;

    public List<CreditOrderEntity> getAll() {
        return creditOrderRepository.findAll();
    }

    public void create(UUID clientId, NewCreditOrderRequestDto newCreditOrderRequestDto) {
        creditOrderRepository.save(new CreditOrderEntity(
                0,
                clientId,
                creditProductRepository.getReferenceById(newCreditOrderRequestDto.getProductId()),
                OrderStatusEnum.pending,
                newCreditOrderRequestDto.getAmountRequested(),
                newCreditOrderRequestDto.getPeriodMonths(),
                newCreditOrderRequestDto.getCreationDate(),
                newCreditOrderRequestDto.getMonthlyIncome(),
                newCreditOrderRequestDto.getMonthlyExpenditure(),
                newCreditOrderRequestDto.getEmployerIdentificationNumber()
        ));
    }
}
