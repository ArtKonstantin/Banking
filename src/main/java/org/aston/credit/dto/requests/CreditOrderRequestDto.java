package org.aston.credit.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;
import org.aston.credit.Constants;

import java.math.BigDecimal;

@Value
@Builder
@Schema(description = "Запрос на создание заявки на кредит")
public class CreditOrderRequestDto {

    @Schema(description = "id кредитного продукта", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = Constants.FIELD_MUST_NOT_BE_BLANK)
    Long productId;

    @Schema(description = "сумма кредита", requiredMode = Schema.RequiredMode.REQUIRED)
    @Positive(message = Constants.FIELD_MUST_BE_POSITIVE)
    @NotBlank(message = Constants.FIELD_MUST_NOT_BE_BLANK)
    BigDecimal amountRequested;

    @Schema(description = "срок кредита в месяцах", requiredMode = Schema.RequiredMode.REQUIRED)
    @Positive(message = Constants.FIELD_MUST_BE_POSITIVE)
    @NotBlank(message = Constants.FIELD_MUST_NOT_BE_BLANK)
    Integer periodMonths;

    @Schema(description = "месячный доход клиента", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 0, message = Constants.FIELD_MUST_NOT_BE_NEGATIVE)
    @NotBlank(message = Constants.FIELD_MUST_NOT_BE_BLANK)
    BigDecimal monthlyIncome;

    @Schema(description = "ежемесячная долговая нагрузка клиента", requiredMode = Schema.RequiredMode.REQUIRED)
    @Min(value = 0, message = Constants.FIELD_MUST_NOT_BE_NEGATIVE)
    @NotBlank(message = Constants.FIELD_MUST_NOT_BE_BLANK)
    BigDecimal monthlyExpenditure;

    @Schema(description = "ИНН клиента", requiredMode = Schema.RequiredMode.REQUIRED)
    @Pattern(regexp = Constants.EMPLOYER_IDENTIFICATION_NUMBER_PATTERN,
            message = Constants.FIELD_NOT_EQUALS_PATTERN)
    @NotBlank(message = Constants.FIELD_MUST_NOT_BE_BLANK)
    String employerIdentificationNumber;
}
