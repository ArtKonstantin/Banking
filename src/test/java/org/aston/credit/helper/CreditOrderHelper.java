package org.aston.credit.helper;

import org.aston.credit.dto.CreditOrderRequestDto;
import org.aston.credit.dto.CreditOrderResponseDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.entity.OrderStatusEnum;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

public class CreditOrderHelper {
    public static final long ID_TEST = 1;
    public static CreditOrderEntity getCreditOrder() {
        CreditOrderEntity creditOrder = new CreditOrderEntity();
        creditOrder.setId(1);
        creditOrder.setClientId(UUID.fromString("0799f8b8-729d-4818-b1ba-5e64f88f6d03"));
        creditOrder.setCreditProduct(getCreditProduct());
        creditOrder.setStatus(OrderStatusEnum.pending);
        creditOrder.setAmount(BigDecimal.valueOf(100000.00));
        creditOrder.setPeriodMonths(12);
        creditOrder.setCreationDate(Date.valueOf("2023-01-01"));
        creditOrder.setAverageMonthlyIncome(BigDecimal.valueOf(10000.00));
        creditOrder.setAverageMonthlyExpenditure(BigDecimal.valueOf(5000.00));
        creditOrder.setEmployerIdentificationNumber("123-45-6789");
        return creditOrder;
    }
    public static CreditProductEntity getCreditProduct() {
        CreditProductEntity creditProduct = new CreditProductEntity();
        creditProduct.setId(1);
        creditProduct.setProductName("Стартовый");
        return creditProduct;
    }
    public static CreditOrderResponseDto getCreditOrderDto() {
        long applicationId = 1;
        long productId = 1;
        String productName = "Стартовый";
        OrderStatusEnum status = OrderStatusEnum.pending;
        BigDecimal amountRequested = BigDecimal.valueOf(100000.00);
        int periodMonths = 12;
        Date creationDate = Date.valueOf("2023-01-01");
        return new CreditOrderResponseDto(applicationId, productId, productName,
                status, amountRequested, periodMonths, creationDate);
    }
    public static CreditOrderRequestDto getCreateCreditOrderDto() {
        long productId = 1;
        BigDecimal amountRequested = BigDecimal.valueOf(100000.00);
        int periodMonths = 12;
        Date creationDate = Date.valueOf("2023-01-01");
        BigDecimal monthlyIncome = BigDecimal.valueOf(10000.00);
        BigDecimal monthlyExpenditure = BigDecimal.valueOf(5000.00);
        String employerIdentificationNumber = "123-45-6789";
        return new CreditOrderRequestDto(productId, amountRequested, periodMonths,
                creationDate, monthlyIncome, monthlyExpenditure, employerIdentificationNumber);
    }
}
