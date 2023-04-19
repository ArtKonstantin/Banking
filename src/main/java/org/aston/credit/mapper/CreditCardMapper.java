package org.aston.credit.mapper;

import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.dto.KafkaPinCodeDto;
import org.aston.credit.dto.requests.ChangeCardLimitRequestDto;
import org.aston.credit.dto.requests.ChangeCardStatusRequestDto;
import org.aston.credit.dto.requests.ChangePinCardRequestDto;
import org.aston.credit.dto.responses.CreditCardForTransferServiceResponseDto;
import org.aston.credit.dto.responses.CreditCardInformationResponseDto;
import org.aston.credit.dto.responses.CreditCardResponseDto;
import org.aston.credit.entity.CreditCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditCardMapper {

    @Mapping(target = "pin", source = "newPin")
    CreditCardEntity newPinDtoToEntity(ChangePinCardRequestDto creditCard);

    CreditCardEntity newStatusDtoToEntity(ChangeCardStatusRequestDto creditCard);

    CreditCardEntity newLimitDtoToEntity(ChangeCardLimitRequestDto creditCard);

    CreditCardEntity toCreditCardByKafka(KafkaCreditCardDto creditCard);

    KafkaCreditCardDto toKafkaCreditCardDto(CreditCardEntity creditCard);

    KafkaPinCodeDto toKafkaPinCodeDto(CreditCardEntity creditCard);

    @Mapping(target = "accountNumber", source = "creditAccount.accountNumber")
    @Mapping(target = "productName", source = "creditAccount.credit.creditOrder.creditProduct.productName")
    @Mapping(target = "principalDebt", source = "creditAccount.principalDebt")
    @Mapping(target = "currencyCode", source = "creditAccount.currencyCode")
    @Mapping(target = "terminationDate", source = "creditAccount.credit.creditAgreement.terminationDate")
    CreditCardResponseDto creditCardToDto(CreditCardEntity creditCard);

    CreditCardForTransferServiceResponseDto creditCardFromTransferServiceToDto (CreditCardEntity creditCard);

    @Mapping(target = "productName", source = "creditAccount.credit.creditOrder.creditProduct.productName")
    @Mapping(target = "currencyCode", source = "creditAccount.currencyCode")
    CreditCardInformationResponseDto creditCardInfoToDto(CreditCardEntity creditCard);

    List<CreditCardInformationResponseDto> creditCardsInfoToDto(List<CreditCardEntity> creditCards);
}
