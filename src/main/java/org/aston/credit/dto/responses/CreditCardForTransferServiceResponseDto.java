package org.aston.credit.dto.responses;

import lombok.Data;
import org.aston.credit.entity.enums.CardStatusEnum;

import java.util.UUID;

@Data
public class CreditCardForTransferServiceResponseDto {
    private UUID id;
    private String cardNumber;
    private CardStatusEnum cardStatus;
}
