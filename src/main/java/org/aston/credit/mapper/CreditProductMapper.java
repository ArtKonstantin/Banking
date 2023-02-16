package org.aston.credit.mapper;

import org.aston.credit.dto.CreditProductResponseDto;
import org.aston.credit.entity.CreditProductEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditProductMapper {
    CreditProductResponseDto creditProductToCreditProductDto(CreditProductEntity creditProduct);
    List<CreditProductResponseDto> creditProductsToCreditProductDto(List<CreditProductEntity> creditProducts);
}
