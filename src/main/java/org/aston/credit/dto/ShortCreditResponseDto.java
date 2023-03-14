package org.aston.credit.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * ДТО с краткой информацией о кредитных продуктах клиента.
 * <p>
 * Используется в эндпоинте: CR.1.
 *
 * @see org.aston.credit.controller.CreditController
 * @see org.aston.credit.service.CreditOrderService
 * @author Gleb Buriak
 */
@Data
@AllArgsConstructor
public class ShortCreditResponseDto {

    @Schema(description = "Идентификатор кредита")
    private Long creditId;

    @Schema(description = "Название кредита")
    private String productName;

    @Schema(description = "Сумма кредита")
    private BigDecimal creditAmount;

    @Schema(description = "Код валюты")
    private String currencyCode;

    @Schema(description = "Срок кредита в месяцах")
    private Integer creditTermMonths;

    @Schema(description = "Срок кредита в годах")
    private BigDecimal creditTermYears;
}
