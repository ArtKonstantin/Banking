package org.aston.credit.mapper;

import org.aston.credit.dto.CreditOrderResponseDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.helper.CreditOrderHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditOrderMapperTest {
    @Test
    void toDto() {
        CreditOrderResponseDto creditOrderDto = new CreditOrderMapperImpl().toDto(CreditOrderHelper.getCreditOrder());
        final CreditOrderResponseDto creditOrder = CreditOrderHelper.getCreditOrderDto();

        assertEquals(creditOrder.getApplicationId(), creditOrderDto.getProductId());
        assertEquals(creditOrder.getAmountRequested(), creditOrderDto.getAmountRequested());
        assertEquals(creditOrder.getProductId(), creditOrderDto.getProductId());
        assertEquals(creditOrder.getProductName(), creditOrderDto.getProductName());
        assertEquals(creditOrder.getStatus(), creditOrderDto.getStatus());
        assertEquals(creditOrder.getPeriodMonths(), creditOrderDto.getPeriodMonths());
        assertEquals(creditOrder.getCreationDate(), creditOrderDto.getCreationDate());
    }

    @Test
    void toEntity() {
        CreditOrderEntity creditOrder = new CreditOrderMapperImpl().toEntity(CreditOrderHelper.getCreateCreditOrderDto());
        final CreditOrderEntity creditOrderEntity = CreditOrderHelper.getCreditOrder();

        assertEquals(CreditOrderHelper.getCreditProduct().getId(), creditOrder.getCreditProduct().getId());
        assertEquals(creditOrderEntity.getAmount(), creditOrder.getAmount());
        assertEquals(creditOrderEntity.getPeriodMonths(), creditOrder.getPeriodMonths());
        assertEquals(creditOrderEntity.getCreationDate(), creditOrder.getCreationDate());
        assertEquals(creditOrderEntity.getAverageMonthlyIncome(), creditOrder.getAverageMonthlyIncome());
        assertEquals(creditOrderEntity.getAverageMonthlyExpenditure(), creditOrder.getAverageMonthlyExpenditure());
        assertEquals(creditOrderEntity.getEmployerIdentificationNumber(), creditOrder.getEmployerIdentificationNumber());
    }
}