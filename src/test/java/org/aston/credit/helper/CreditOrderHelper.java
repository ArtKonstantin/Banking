package org.aston.credit.helper;

import org.aston.credit.dto.CreditOrderRequestDto;
import org.aston.credit.dto.CreditOrderResponseDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.entity.OrderStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public class CreditOrderHelper {
    public static final long ID_TEST = 1;
    public static Calendar calendar = new GregorianCalendar(2023, 1, 1);
    public static final UUID CLIENT_ID = UUID.fromString("0799f8b8-729d-4818-b1ba-5e64f88f6d03");

    public static CreditOrderEntity getCreditOrder() {
        CreditOrderEntity creditOrder = new CreditOrderEntity();
        creditOrder.setId(1);
        creditOrder.setClientId(UUID.fromString("0799f8b8-729d-4818-b1ba-5e64f88f6d03"));
        creditOrder.setCreditProduct(getCreditProduct());
        creditOrder.setStatus(OrderStatusEnum.APPROVED_BY_BANK);
        creditOrder.setAmount(BigDecimal.valueOf(100000.00));
        creditOrder.setPeriodMonths(12);
        creditOrder.setCreationDate(LocalDate.now());
        creditOrder.setAverageMonthlyIncome(BigDecimal.valueOf(10000.00));
        creditOrder.setAverageMonthlyExpenditure(BigDecimal.valueOf(5000.00));
        creditOrder.setEmployerIdentificationNumber("123-45-6789");
        return creditOrder;
    }

    public static CreditOrderEntity getCreditCreate() {
        CreditOrderEntity creditOrder = new CreditOrderEntity();
        creditOrder.setAmount(BigDecimal.valueOf(100000.00));
        creditOrder.setPeriodMonths(12);
        creditOrder.setCreationDate(LocalDate.now());
        creditOrder.setAverageMonthlyIncome(BigDecimal.valueOf(10000.00));
        creditOrder.setAverageMonthlyExpenditure(BigDecimal.valueOf(5000.00));
        creditOrder.setEmployerIdentificationNumber("123-45-6789");
        return creditOrder;
    }

    public static CreditOrderEntity getCreditOrderApproved() {
        CreditOrderEntity creditOrder = new CreditOrderEntity();
        creditOrder.setId(1);
        creditOrder.setStatus(OrderStatusEnum.APPROVED_BY_CLIENT);
        return creditOrder;
    }

    public static CreditProductEntity getCreditProduct() {
        CreditProductEntity creditProduct = new CreditProductEntity();
        creditProduct.setId(1);
        creditProduct.setProductName("Стартовый");
        creditProduct.setProductIsActive(false);
        creditProduct.setMaxSum(BigDecimal.valueOf(90000));
        return creditProduct;
    }

    public static CreditOrderResponseDto getCreditOrderDto() {
        return CreditOrderResponseDto.builder().
                applicationId(1).
                productId(1).
                productName("Стартовый").
                status(OrderStatusEnum.APPROVED_BY_BANK).
                amountRequested(BigDecimal.valueOf(100000.00)).
                periodMonths(12).
                creationDate(LocalDate.now()).
                build();
    }

    public static CreditOrderRequestDto getCreateCreditOrderDto() {
        return CreditOrderRequestDto.builder().
                productId(1).
                amountRequested(BigDecimal.valueOf(100000.00)).
                periodMonths(12).
                creationDate(LocalDate.now()).
                monthlyIncome(BigDecimal.valueOf(10000.00)).
                monthlyExpenditure(BigDecimal.valueOf(5000.00)).
                employerIdentificationNumber("123-45-6789").
                build();
    }
}
