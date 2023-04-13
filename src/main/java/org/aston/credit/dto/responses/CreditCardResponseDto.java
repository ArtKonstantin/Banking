package org.aston.credit.dto.responses;

import lombok.Data;
import org.aston.credit.entity.enums.CardStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CreditCardResponseDto {
    private String accountNumber;
    private int cardBalance;
    private String holderName;
    private LocalDate expirationDate;
    private String paymentSystem;
    private CardStatusEnum cardStatus;
    private int transactionLimit;
    private String productName;
    private BigDecimal principalDebt;
    private BigDecimal limit;
    private String currencyCode;
    private LocalDate terminationDate;
}
