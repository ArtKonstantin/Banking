package org.aston.credit.mapper;

import org.aston.credit.dto.CreditOrderRejectDto;
import org.aston.credit.dto.CreditOrderRequestDto;
import org.aston.credit.dto.CreditOrderResponseDto;
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

    CreditOrderEntity toStatus(CreditOrderRejectDto creditOrderReject);

    List<CreditOrderResponseDto> toDtoList(List<CreditOrderEntity> creditOrders);
}
