package org.aston.credit.dto.responses;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreditCardInformationResponseDto {
    private String cardNumber;
    private int cardBalance;
    private String currencyCode;
    private String paymentSystem;
    private LocalDate expirationDate;
    private String productName;
}
