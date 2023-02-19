package org.aston.credit.mapper;

import org.aston.credit.dto.CreditOrderRequestDto;
import org.aston.credit.dto.CreditOrderResponseDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
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

    List<CreditOrderResponseDto> toDtoList(List<CreditOrderEntity> creditOrders);
}
