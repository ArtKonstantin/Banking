package org.aston.credit.mapper;

import org.aston.credit.dto.requests.ChangeCardLimitRequestDto;
import org.aston.credit.dto.requests.ChangeCardStatusRequestDto;
import org.aston.credit.dto.requests.ChangePinCardRequestDto;
import org.aston.credit.entity.CreditCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditCardMapper {

    @Mapping(target = "pin", source = "newPin")
    CreditCardEntity newPinToEntity(ChangePinCardRequestDto creditCard);

    CreditCardEntity newStatusToEntity (ChangeCardStatusRequestDto creditCard);

    CreditCardEntity newLimitToEntity (ChangeCardLimitRequestDto creditCard);

}
