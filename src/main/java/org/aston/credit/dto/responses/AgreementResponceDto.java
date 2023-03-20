package org.aston.credit.dto.responses;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AgreementResponceDto {
    private long agreementId;
    private String creditCurrencyCode;
    private BigDecimal principalDebt;
    private BigDecimal interestDebt;
    private BigDecimal principal;
    private BigDecimal interest;
}
