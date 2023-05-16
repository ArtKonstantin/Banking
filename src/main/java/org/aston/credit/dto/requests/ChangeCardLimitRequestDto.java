package org.aston.credit.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;
import org.aston.credit.Constants;

@Value
@Builder
@Schema(description = "Запрос нового лимита кредитной карты")
public class ChangeCardLimitRequestDto {

    @Schema(description = "Номер кредитной карты", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = Constants.CREDIT_CARD_BLANK)
    @Pattern(regexp = Constants.CARD_NUMBER_PATTERN, message = Constants.CREDIT_CARD_INVALID)
    String cardNumber;

    @Schema(description = "Новый лимит на операции по карте ")
    @NotBlank(message = "Кредитный лимит не может быть пустым")
    @Positive(message = Constants.FIELD_MUST_BE_POSITIVE)
    @Max(value = 500000, message = "лимит по операциям не может превышать 500000")
    Integer transactionLimit;
}
