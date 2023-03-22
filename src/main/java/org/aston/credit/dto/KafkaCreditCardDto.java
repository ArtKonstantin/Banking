package org.aston.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aston.credit.entity.CardStatusEnum;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaCreditCardDto {
    private String cardNumber;
    private CardStatusEnum cardStatus;
}
