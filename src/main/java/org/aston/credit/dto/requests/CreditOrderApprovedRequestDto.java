package org.aston.credit.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@Schema(description = "Запрос на получение данных о кредитных заявках")
public class CreditOrderApprovedRequestDto {

    @Schema(description = "Номер кредитной заявки")
    @NotEmpty(message = "Номер кредитной заявки не может быть пустым")
    Long id;
}
