package org.aston.credit.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.aston.credit.entity.enums.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class CreditOrderResponseDto {
    private long applicationId;
    private long productId;
    private String productName;
    private OrderStatusEnum status;
    private BigDecimal amountRequested;
    private int periodMonths;
    private LocalDate creationDate;
}
