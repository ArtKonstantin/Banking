package org.aston.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KafkaPinCodeDto {
    private String cardNumber;
    private String pin;
}
