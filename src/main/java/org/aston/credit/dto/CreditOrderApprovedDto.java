package org.aston.credit.dto;

import lombok.Value;
import org.aston.credit.entity.OrderStatusEnum;

@Value
public class CreditOrderApprovedDto {
    private long id;
    private OrderStatusEnum status;
}