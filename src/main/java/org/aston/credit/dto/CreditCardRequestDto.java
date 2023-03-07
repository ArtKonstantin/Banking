package org.aston.credit.dto;

import lombok.Builder;
import lombok.Value;
import org.aston.credit.entity.CardStatusEnum;

@Value
@Builder
public class CreditCardRequestDto {
    private String cardNumber;
    private CardStatusEnum cardStatus;
    private String newPin;
    private String transactionLimit;
}
