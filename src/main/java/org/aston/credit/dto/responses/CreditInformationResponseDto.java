package org.aston.credit.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CreditInformationResponseDto {

    @Schema(description = "Идентификатор кредита")
    private Long creditId;

    @Schema(description = "Название кредита")
    private String name;

    @Schema(description = "Код валюты")
    private String currencyCode;

    @Schema(description = "Номер счёта")
    private String accountNumber;

    @Schema(description = "Кредитный лимит")
    private BigDecimal creditLimit;

    @Schema(description = "Ставка по кредиту")
    private BigDecimal interestRate;

    @Schema(description = "Дата прекращения действия кредитного договора")
    private String terminationDate;

    @Schema(description = "Ближайшая дата платежа")
    private LocalDate paymentDate;
}
