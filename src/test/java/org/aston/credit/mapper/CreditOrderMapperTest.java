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
        assertEquals(CreditOrderHelper.getCreditOrderDto().getApplicationId(), creditOrderDto.getProductId());
        assertEquals(CreditOrderHelper.getCreditOrderDto().getAmountRequested(), creditOrderDto.getAmountRequested());
        assertEquals(CreditOrderHelper.getCreditOrderDto().getProductId(), creditOrderDto.getProductId());
        assertEquals(CreditOrderHelper.getCreditOrderDto().getProductName(), creditOrderDto.getProductName());
        assertEquals(CreditOrderHelper.getCreditOrderDto().getStatus(), creditOrderDto.getStatus());
        assertEquals(CreditOrderHelper.getCreditOrderDto().getPeriodMonths(), creditOrderDto.getPeriodMonths());
        assertEquals(CreditOrderHelper.getCreditOrderDto().getCreationDate(), creditOrderDto.getCreationDate());
    }
    @Test
    void toEntity() {
        CreditOrderEntity creditOrder = new CreditOrderMapperImpl().toEntity(CreditOrderHelper.getCreateCreditOrderDto());
        assertEquals(CreditOrderHelper.getCreditProduct().getId(), creditOrder.getCreditProduct().getId());
        assertEquals(CreditOrderHelper.getCreditOrder().getAmount(), creditOrder.getAmount());
        assertEquals(CreditOrderHelper.getCreditOrder().getPeriodMonths(), creditOrder.getPeriodMonths());
        assertEquals(CreditOrderHelper.getCreditOrder().getCreationDate(), creditOrder.getCreationDate());
        assertEquals(CreditOrderHelper.getCreditOrder().getAverageMonthlyIncome(), creditOrder.getAverageMonthlyIncome());
        assertEquals(CreditOrderHelper.getCreditOrder().getAverageMonthlyExpenditure(), creditOrder.getAverageMonthlyExpenditure());
        assertEquals(CreditOrderHelper.getCreditOrder().getEmployerIdentificationNumber(), creditOrder.getEmployerIdentificationNumber());
    }
}