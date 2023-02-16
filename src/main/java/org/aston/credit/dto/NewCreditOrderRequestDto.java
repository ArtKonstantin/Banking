package org.aston.credit.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class NewCreditOrderRequestDto {
    private long productId;
    private BigDecimal amountRequested;
    private int periodMonths;
    private Date creationDate;
    private BigDecimal monthlyIncome;
    private BigDecimal monthlyExpenditure;
    private String employerIdentificationNumber;
}
