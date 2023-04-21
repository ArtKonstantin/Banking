package org.aston.credit.dto.responses;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class CreditCardInformationResponseDto {
    private UUID id;
    private String cardNumber;
    private int cardBalance;
    private String currencyCode;
    private String paymentSystem;
    private LocalDate expirationDate;
    private String productName;
}
