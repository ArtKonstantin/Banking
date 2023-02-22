package org.aston.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aston.credit.entity.PaymentScheduleEntity;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ScheduleResponseDto {
    private String accountNumber;
    private long agreementId;
    private BigDecimal currentPrincipalAmount;
    private BigDecimal currentInterestAmount;
    private List<PaymentScheduleEntity> payments;
}
