package org.aston.credit.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.aston.credit.entity.enums.CalculationModeEnum;

import java.math.BigDecimal;

@Data
public class CreditProductResponseDto {

    @Schema(description = "Идентификатор кредитного продукта")
    private long id;

    @Schema(description = "Название кредита")
    private String name;

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

    @Schema(description = "Нужно ли обеспечение")
    private boolean needGuarantees;

    @Schema(description = "Возможность получить кредит наличкой")
    private boolean deliveryInCash;

    @Schema(description = "Возможность раннего погашения")
    private boolean earlyRepayment;

    @Schema(description = "Нужна ли справка о доходах")
    private boolean needIncomeDetails;

    @Schema(description = "Минимальный срок кредита")
    private int minPeriodMonths;

    @Schema(description = "Максимальный срок кредита")
    private int maxPeriodMonths;

    @Schema(description = "Активный продукт либо недействительный")
    private boolean active;

    @Schema(description = "Информация о кредите")
    private String details;

    @Schema(description = "Схема расчета платежей")
    private CalculationModeEnum calculationMode;

    @Schema(description = "Грейс-период в месяцах")
    private int gracePeriodMonths;

    @Schema(description = "Постоянная ставка")
    private BigDecimal interestRate;

}
