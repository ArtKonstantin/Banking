package org.aston.credit.mapper;

import org.aston.credit.dto.CreditCardRequestDto;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.entity.CreditCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditCardMapper {

    @Mapping(target = "pin", source = "newPin")
    CreditCardEntity toEntity(CreditCardRequestDto creditCard);

    CreditCardEntity toEntityByKafka(KafkaCreditCardDto creditCard);

    KafkaCreditCardDto toKafkaDto(CreditCardEntity creditCard);
}
