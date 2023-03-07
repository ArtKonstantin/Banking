package org.aston.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

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
public class CreditInformationResponseDTO {

    private Long creditId;

    private String name;

    private String currencyCode;

    private String accountNumber;

    private BigDecimal creditLimit;

    private BigDecimal interestRate;

    private String terminationDate;

    private Date paymentDate;
}
