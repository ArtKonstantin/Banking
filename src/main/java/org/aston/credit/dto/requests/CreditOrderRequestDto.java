package org.aston.credit.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
@Schema(description = "Запрос на создание заявки на кредит")
public class CreditOrderRequestDto {

    @Schema(description = "id кредитного продукта", requiredMode = Schema.RequiredMode.REQUIRED)
    Long productId;

    @Schema(description = "сумма кредита", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 0)
    BigDecimal amountRequested;

    @Schema(description = "срок кредита в месяцах", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 0)
    Integer periodMonths;

    @Schema(description = "месячный доход клиента", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 0)
    BigDecimal monthlyIncome;

    @Schema(description = "ежемесячная долговая нагрузка клиента", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 0)
    BigDecimal monthlyExpenditure;

    @Schema(description = "ИНН клиента", requiredMode = Schema.RequiredMode.REQUIRED)
    String employerIdentificationNumber;
}
