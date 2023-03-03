package org.aston.credit.mapper;

import org.aston.credit.dto.CreditProductResponseDto;
import org.aston.credit.helper.CreditProductHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditProductMapperTest {
    @Test
    void toDto() {
        CreditProductResponseDto creditProductDto = new CreditProductMapperImpl().toDto(CreditProductHelper.getCreditProduct());
        final CreditProductResponseDto creditProduct = CreditProductHelper.getCreditProductDto();

        assertEquals(creditProduct.getId(), creditProductDto.getId());
        assertEquals(creditProduct.getProductName(), creditProductDto.getProductName());
        assertEquals(creditProduct.getMinSum(), creditProductDto.getMinSum());
        assertEquals(creditProduct.getMaxSum(), creditProductDto.getMaxSum());
        assertEquals(creditProduct.getCurrencyCode(), creditProductDto.getCurrencyCode());
        assertEquals(creditProduct.getMinInterestRate(), creditProductDto.getMinInterestRate());
        assertEquals(creditProduct.getMaxInterestRate(), creditProductDto.getMaxInterestRate());
        assertEquals(creditProduct.isNeedGuarantees(), creditProductDto.isNeedGuarantees());
        assertEquals(creditProduct.isDeliveryInCash(), creditProductDto.isDeliveryInCash());
        assertEquals(creditProduct.isEarlyRepayment(), creditProductDto.isEarlyRepayment());
        assertEquals(creditProduct.getMinPeriodMonths(), creditProductDto.getMinPeriodMonths());
        assertEquals(creditProduct.getMaxPeriodMonths(), creditProductDto.getMaxPeriodMonths());
        assertEquals(creditProduct.isProductIsActive(), creditProductDto.isProductIsActive());
        assertEquals(creditProduct.getProductDetails(), creditProductDto.getProductDetails());
        assertEquals(creditProduct.getCalculationMode(), creditProductDto.getCalculationMode());
        assertEquals(creditProduct.getGracePeriodMonths(), creditProductDto.getGracePeriodMonths());
        assertEquals(creditProduct.isNeedIncomeDetails(), creditProductDto.isNeedIncomeDetails());
    }
}
