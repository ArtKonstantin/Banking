package org.aston.credit.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import org.aston.credit.Constants;

@Value
@Builder
@Schema(description = "Запрос на смену PIN-кода кредитной карты")
public class ChangePinCardRequestDto {
    @Schema(description = "Номер кредитной карты", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = Constants.CREDIT_CARD_BLANK)
    @Pattern(regexp = "\\d{16}", message = Constants.CREDIT_CARD_INVALID)
    String cardNumber;

    @Schema(description = "Новый PIN-код карты")
    @NotBlank(message = "PIN-код не может быть пустым")
    @Pattern(regexp = "\\d{4}", message = "PIN-код карты должен быть четырехзначным числом")
    String newPin;
}
