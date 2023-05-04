package org.aston.credit.mapper;

import org.aston.credit.dto.requests.CreditOrderApprovedRequestDto;
import org.aston.credit.dto.requests.CreditOrderRequestDto;
import org.aston.credit.dto.responses.CreditOrderResponseDto;
import org.aston.credit.dto.responses.ShortCreditResponseDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditOrderMapper {

    @Mapping(target = "applicationId", source = "id")
    @Mapping(target = "amountRequested", source = "amount")
    @Mapping(target = "productId", source = "creditProduct.id")
    @Mapping(target = "productName", source = "creditProduct.productName")
    CreditOrderResponseDto toDto(CreditOrderEntity creditOrder);

    @Mapping(target = "averageMonthlyIncome", source = "monthlyIncome")
    @Mapping(target = "amount", source = "amountRequested")
    @Mapping(target = "creditProduct.id", source = "productId")
    @Mapping(target = "averageMonthlyExpenditure", source = "monthlyExpenditure")
    CreditOrderEntity toEntity(CreditOrderRequestDto creditOrder);

    CreditOrderEntity toStatus(CreditOrderApprovedRequestDto creditOrderApproved);

    List<CreditOrderResponseDto> toDtoList(List<CreditOrderEntity> creditOrders);

    @Mapping(target = "creditId", source = "id")
    @Mapping(target = "productName", source = "creditProduct.productName")
    @Mapping(target = "creditAmount", source = "amount")
    @Mapping(target = "currencyCode", source = "credit.currencyCode")
    @Mapping(target = "creditTermMonths", source = "periodMonths")
    @Mapping(target = "creditTermYears", expression = "java(creditTermYears = BigDecimal.valueOf(creditTermMonths/12))")
    ShortCreditResponseDto toShortDto(CreditOrderEntity creditOrder);

    List<ShortCreditResponseDto> toListShortDto(List<CreditOrderEntity> credits);
}
