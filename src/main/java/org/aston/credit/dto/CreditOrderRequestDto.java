package org.aston.credit.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.util.Date;

@Value
public class CreditOrderRequestDto {
    private long productId;
    private BigDecimal amountRequested;
    private int periodMonths;
    private Date creationDate;
    private BigDecimal monthlyIncome;
    private BigDecimal monthlyExpenditure;
    private String employerIdentificationNumber;
}
