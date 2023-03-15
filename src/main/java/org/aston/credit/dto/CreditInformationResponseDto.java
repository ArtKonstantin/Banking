package org.aston.credit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * ДТО с полной информацией о кредитном продукте клиента.
 * <p>
 * Используется в эндпоинтах: CR.2.
 *
 * @author Gleb Buriak
 * @see org.aston.credit.controller.CreditController
 * @see org.aston.credit.service.CreditService
 */
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

    @Schema(description = "Дата последнего платежа по кредиту")
    private String terminationDate;

    @Schema(description = "Ближайшая дата платежа")
    private LocalDate paymentDate;
}
