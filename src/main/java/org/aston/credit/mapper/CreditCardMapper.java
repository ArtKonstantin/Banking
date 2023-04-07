package org.aston.credit.mapper;

import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.dto.KafkaPinCodeDto;
import org.aston.credit.dto.requests.ChangeCardLimitRequestDto;
import org.aston.credit.dto.requests.ChangeCardStatusRequestDto;
import org.aston.credit.dto.requests.ChangePinCardRequestDto;
import org.aston.credit.dto.responses.CreditCardResponseDto;
import org.aston.credit.entity.CreditCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.time.LocalDate;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditCardMapper {

    @Mapping(target = "pin", source = "newPin")
    CreditCardEntity newPinToEntity(ChangePinCardRequestDto creditCard);

    CreditCardEntity newStatusToEntity (ChangeCardStatusRequestDto creditCard);

    CreditCardEntity newLimitToEntity (ChangeCardLimitRequestDto creditCard);

    CreditCardEntity toEntityByKafka(KafkaCreditCardDto creditCard);

    KafkaCreditCardDto toKafkaDto(CreditCardEntity creditCard);

    KafkaPinCodeDto toKafkaPinCodeDto(CreditCardEntity creditCard);

    @Mapping(target = "accountNumber", source = "creditAccount.accountNumber")
    @Mapping(target = "productName", source = "creditAccount.credit.creditOrder.creditProduct.productName")
    @Mapping(target = "principalDebt", source = "creditAccount.principalDebt")
    @Mapping(target = "currencyCode", source = "creditAccount.currencyCode")
    @Mapping(target = "terminationDate", source = "creditAccount.credit.creditAgreement.terminationDate")
    CreditCardResponseDto toDto(CreditCardEntity creditCard);
}
