package org.aston.credit.mapper;

import org.aston.credit.dto.responses.CreditActiveProductResponseDto;
import org.aston.credit.dto.responses.CreditProductResponseDto;
import org.aston.credit.entity.CreditProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditProductMapper {

    @Mapping(target = "name", source = "productName")
    @Mapping(target = "details", source = "productDetails")
    CreditActiveProductResponseDto toActiveDto(CreditProductEntity creditProduct);

    List<CreditActiveProductResponseDto> toActiveDtoList(List<CreditProductEntity> creditProducts);

    @Mapping(target = "name", source = "productName")
    @Mapping(target = "details", source = "productDetails")
    @Mapping(target = "active", source = "productIsActive")
    CreditProductResponseDto toDto(CreditProductEntity creditProduct);
}
