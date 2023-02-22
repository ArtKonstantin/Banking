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
        creditProduct.setCalculationMode(CalculationModeEnum.differentiated);
        creditProduct.setGracePeriodMonths(0);
        creditProduct.setNeedIncomeDetails(true);
        return creditProduct;
    }

    public static CreditProductResponseDto getCreditProductDto() {
        long id = 1;
        String productName = "Стартовый";
        BigDecimal minSum = BigDecimal.valueOf(1000.00);
        BigDecimal maxSum = BigDecimal.valueOf(1000000.00);
        String currencyCode = "RUB";
        BigDecimal minInterestRate = BigDecimal.valueOf(0.35);
        BigDecimal maxInterestRate = BigDecimal.valueOf(0.30);
        boolean needGuarantees = false;
        boolean deliveryInCash = false;
        boolean earlyRepayment = true;
        int minPeriodMonths = 6;
        int maxPeriodMonths = 36;
        boolean productIsActive = true;
        String productDetails = "Стандартный кредит";
        CalculationModeEnum calculationMode = CalculationModeEnum.differentiated;
        int gracePeriodMonths = 0;
        boolean needIncomeDetails = true;
        return new CreditProductResponseDto(id, productName, minSum, maxSum, currencyCode, minInterestRate,
                maxInterestRate, needGuarantees, deliveryInCash, earlyRepayment, minPeriodMonths,
                maxPeriodMonths, productIsActive, productDetails, calculationMode, gracePeriodMonths,
                needIncomeDetails);
    }
}
