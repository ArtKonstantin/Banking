package org.aston.credit.mapper;

import org.aston.credit.dto.CreditCardRequestDto;
import org.aston.credit.entity.CreditCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditCardMapper {
    CreditCardEntity toEntity(CreditCardRequestDto creditCard);
}
