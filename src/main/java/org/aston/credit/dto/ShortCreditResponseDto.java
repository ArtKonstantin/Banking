package org.aston.credit.dto;

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

    private Long creditId;

    private String productName;

    private BigDecimal creditAmount;

    private String currencyCode;

    private Integer creditTermMonths;

    private BigDecimal creditTermYears;
}
