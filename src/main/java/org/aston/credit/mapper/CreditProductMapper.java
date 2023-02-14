package org.aston.credit.mapper;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditProductResponceDto;
import org.aston.credit.entity.CreditProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditProductMapper {
    private final ModelMapper modelMapper;

    public CreditProductResponceDto mapperToDto(CreditProductEntity entity){
        return modelMapper.map(entity, CreditProductResponceDto.class);
    }
}
