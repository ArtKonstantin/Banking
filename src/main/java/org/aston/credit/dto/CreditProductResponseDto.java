package org.aston.credit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.aston.credit.entity.CalculationModeEnum;

import java.math.BigDecimal;

@Data
public class CreditProductResponseDto {

    @Schema(description = "Идентификатор кредитного продукта")
    private long id;

    @Schema(description = "Название кредита")
    private String productName;

    @Schema(description = "Минимальная сумма кредита")
    private BigDecimal minSum;

    @Schema(description = "Максимальная сумма кредита")
    private BigDecimal maxSum;

    @Schema(description = "Код валюты кредита")
    private String currencyCode;

    @Schema(description = "Минимальная процентная ставка")
    private BigDecimal minInterestRate;

    @Schema(description = "Максимальная процентная ставка")
    private BigDecimal maxInterestRate;

    private boolean needGuarantees;

    @Schema(description = "Возможность получить кредит наличкой")
    private boolean deliveryInCash;

    @Schema(description = "Возможность раннего погашения")
    private boolean earlyRepayment;

    @Schema(description = "Минимальный срок кредита")
    private int minPeriodMonths;

    @Schema(description = "Максимальный срок кредита")
    private int maxPeriodMonths;

    @Schema(description = "Активный продукт либо недействительный")
    private boolean productIsActive;

    @Schema(description = "Информация о кредите")
    private String productDetails;

    private CalculationModeEnum calculationMode;

    private int gracePeriodMonths;

    private boolean needIncomeDetails;
}
