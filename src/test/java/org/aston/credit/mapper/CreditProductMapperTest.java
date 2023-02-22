package org.aston.credit.mapper;

import org.aston.credit.dto.CreditProductResponseDto;
import org.aston.credit.helper.CreditProductHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditProductMapperTest {
    @Test
    void toDto() {
        CreditProductResponseDto creditProductDto = new CreditProductMapperImpl().toDto(CreditProductHelper.getCreditProduct());
        assertEquals(CreditProductHelper.getCreditProductDto().getId(), creditProductDto.getId());
        assertEquals(CreditProductHelper.getCreditProductDto().getProductName(), creditProductDto.getProductName());
        assertEquals(CreditProductHelper.getCreditProductDto().getMinSum(), creditProductDto.getMinSum());
        assertEquals(CreditProductHelper.getCreditProductDto().getMaxSum(), creditProductDto.getMaxSum());
        assertEquals(CreditProductHelper.getCreditProductDto().getCurrencyCode(), creditProductDto.getCurrencyCode());
        assertEquals(CreditProductHelper.getCreditProductDto().getMinInterestRate(), creditProductDto.getMinInterestRate());
        assertEquals(CreditProductHelper.getCreditProductDto().getMaxInterestRate(), creditProductDto.getMaxInterestRate());
        assertEquals(CreditProductHelper.getCreditProductDto().isNeedGuarantees(), creditProductDto.isNeedGuarantees());
        assertEquals(CreditProductHelper.getCreditProductDto().isDeliveryInCash(), creditProductDto.isDeliveryInCash());
        assertEquals(CreditProductHelper.getCreditProductDto().isEarlyRepayment(), creditProductDto.isEarlyRepayment());
        assertEquals(CreditProductHelper.getCreditProductDto().getMinPeriodMonths(), creditProductDto.getMinPeriodMonths());
        assertEquals(CreditProductHelper.getCreditProductDto().getMaxPeriodMonths(), creditProductDto.getMaxPeriodMonths());
        assertEquals(CreditProductHelper.getCreditProductDto().isProductIsActive(), creditProductDto.isProductIsActive());
        assertEquals(CreditProductHelper.getCreditProductDto().getProductDetails(), creditProductDto.getProductDetails());
        assertEquals(CreditProductHelper.getCreditProductDto().getCalculationMode(), creditProductDto.getCalculationMode());
        assertEquals(CreditProductHelper.getCreditProductDto().getGracePeriodMonths(), creditProductDto.getGracePeriodMonths());
        assertEquals(CreditProductHelper.getCreditProductDto().isNeedIncomeDetails(), creditProductDto.isNeedIncomeDetails());
    }
}
