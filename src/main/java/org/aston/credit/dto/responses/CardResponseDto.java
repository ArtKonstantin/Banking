package org.aston.credit.dto.responses;

import lombok.Data;
import org.aston.credit.entity.enums.CardStatusEnum;

import java.util.UUID;

@Data
public class CardResponseDto {
    private UUID id;
    private String cardNumber;
    private CardStatusEnum cardStatusName;
}
