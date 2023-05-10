package org.aston.credit.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreditActiveProductResponseDto {

    @Schema(description = "Идентификатор кредитного продукта")
    private long id;

    @Schema(description = "Название кредита")
    private String name;

    @Schema(description = "Информация о кредите")
    private String details;

    @Schema(description = "Постоянная ставка")
    private BigDecimal interestRate;

}
