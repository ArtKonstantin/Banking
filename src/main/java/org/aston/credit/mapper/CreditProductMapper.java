package org.aston.credit.mapper;

import org.aston.credit.dto.responses.CreditProductResponseDto;
import org.aston.credit.entity.CreditProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditProductMapper {
    CreditProductResponseDto toDto(CreditProductEntity creditProduct);

    List<CreditProductResponseDto> toDtoList(List<CreditProductEntity> creditProducts);
}
