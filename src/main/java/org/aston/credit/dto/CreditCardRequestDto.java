package org.aston.credit.dto;

import lombok.Value;
import org.aston.credit.entity.CardStatusEnum;

@Value
public class CreditCardRequestDto {
    private String cardNumber;
    private CardStatusEnum cardStatus;
}
