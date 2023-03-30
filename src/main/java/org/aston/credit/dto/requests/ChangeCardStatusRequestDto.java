package org.aston.credit.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;
import org.aston.credit.Constants;
import org.aston.credit.entity.enums.CardStatusEnum;

@Value
@Builder
@Schema(description = "Запрос смены статуса кредитной карты")
public class ChangeCardStatusRequestDto {

    @Schema(description = "Номер кредитной карты", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = Constants.CREDIT_CARD_BLANK)
    @Pattern(regexp = "\\d{16}", message = Constants.CREDIT_CARD_INVALID)
    String cardNumber;

    @Schema(description = "Статус кредитной карты")
    CardStatusEnum cardStatus;
}
