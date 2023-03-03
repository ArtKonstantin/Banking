package org.aston.credit.helper;

import org.aston.credit.dto.CreditProductResponseDto;
import org.aston.credit.entity.CalculationModeEnum;
import org.aston.credit.entity.CreditProductEntity;

import java.math.BigDecimal;

public class CreditProductHelper {
    public static CreditProductEntity getCreditProduct() {
        CreditProductEntity creditProduct = new CreditProductEntity();
        creditProduct.setId(1);
        creditProduct.setProductName("Стартовый");
        creditProduct.setMinSum(BigDecimal.valueOf(1000.00));
        creditProduct.setMaxSum(BigDecimal.valueOf(1000000.00));
        creditProduct.setCurrencyCode("RUB");
        creditProduct.setMinInterestRate(BigDecimal.valueOf(0.35));
        creditProduct.setMaxInterestRate(BigDecimal.valueOf(0.30));
        creditProduct.setNeedGuarantees(false);
        creditProduct.setDeliveryInCash(false);
        creditProduct.setEarlyRepayment(true);
        creditProduct.setMinPeriodMonths(6);
        creditProduct.setMaxPeriodMonths(36);
        creditProduct.setProductIsActive(true);
        creditProduct.setProductDetails("Стандартный кредит");
        creditProduct.setCalculationMode(CalculationModeEnum.DIFFERENTIATED);
        creditProduct.setGracePeriodMonths(0);
        creditProduct.setNeedIncomeDetails(true);
        return creditProduct;
    }

    public static CreditProductResponseDto getCreditProductDto() {
        return new CreditProductResponseDto(
                1,
                "Стартовый",
                BigDecimal.valueOf(1000.00),
                BigDecimal.valueOf(1000000.00),
                "RUB",
                BigDecimal.valueOf(0.35),
                BigDecimal.valueOf(0.30),
                false,
                false,
                true,
                6,
                36,
                true,
                "Стандартный кредит",
                CalculationModeEnum.DIFFERENTIATED,
                0,
                true
        );
    }
}
