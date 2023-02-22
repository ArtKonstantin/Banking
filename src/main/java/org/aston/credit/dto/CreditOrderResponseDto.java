package org.aston.credit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.aston.credit.entity.OrderStatusEnum;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@AllArgsConstructor
public class CreditOrderResponseDto {
    private long applicationId;
    private long productId;
    private String productName;
    private OrderStatusEnum status;
    private BigDecimal amountRequested;
    private int periodMonths;
    private Date creationDate;
}
