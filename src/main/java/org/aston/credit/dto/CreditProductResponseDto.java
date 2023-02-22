package org.aston.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aston.credit.entity.CalculationModeEnum;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class CreditProductResponseDto {
    private long id;
    private String productName;
    private BigDecimal minSum;
    private BigDecimal maxSum;
    private String currencyCode;
    private BigDecimal minInterestRate;
    private BigDecimal maxInterestRate;
    private boolean needGuarantees;
    private boolean deliveryInCash;
    private boolean earlyRepayment;
    private int minPeriodMonths;
    private int maxPeriodMonths;
    private boolean productIsActive;
    private String productDetails;
    private CalculationModeEnum calculationMode;
    private int gracePeriodMonths;
    private boolean needIncomeDetails;
}
