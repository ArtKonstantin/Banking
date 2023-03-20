package org.aston.credit.mapper;

import org.aston.credit.dto.responses.AgreementResponceDto;
import org.aston.credit.dto.responses.CreditInformationResponseDto;
import org.aston.credit.dto.responses.ScheduleResponseDto;
import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.entity.PaymentScheduleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditMapper {

    /**
     * CR.2 - Отправка полной информации о кредитном продуктах клиента.
     * <p>
     * Метод лдя маппинга {@link CreditEntity} в ДТО с полной информацией о кредитном продукте клиента.
     * <p>
     * В маппинге учавствуют следующие поля сущностей: <i>creditId</i>, <i>productName</i>, <i>currencyCode</i>,
     * <i>accountNumber</i>, <i>creditLimit</i>, <i>interestRate</i>, <i>terminationDate</i>
     *
     * @param credit сущность которую необходимо смапить
     * @return {@link CreditInformationResponseDto} с информацией о кредитных продуктах клиента
     */
    @Mapping(target = "creditId", source = "id")
    @Mapping(target = "name", source = "creditOrder.creditProduct.productName")
    @Mapping(target = "currencyCode", source = "currencyCode")
    @Mapping(target = "accountNumber", source = "creditAccount.accountNumber")
    @Mapping(target = "creditLimit", source = "creditLimit")
    @Mapping(target = "interestRate", source = "interestRate")
    @Mapping(target = "terminationDate", source = "creditAgreement.terminationDate")
    @Mapping(target = "paymentDate", expression = "java(credit.getCreditAccount().getPaymentSchedule().stream()" +
            ".findFirst().get().getPaymentDate())")
    CreditInformationResponseDto toInformationDto(CreditEntity credit);

    @Mapping(target = "accountNumber", source = "creditAccount.accountNumber")
    @Mapping(target = "agreementId", source = "creditAgreement.id")
    @Mapping(target = "currentPrincipalAmount", source = "creditAccount.principalDebt")
    @Mapping(target = "currentInterestAmount", source = "creditAccount.interestDebt")
    @Mapping(target = "payments", source = "creditAccount.paymentSchedule")
    ScheduleResponseDto toDto(CreditEntity credit);

    @Mapping(target = "agreementId", source = "credit.creditAgreement.id")
    @Mapping(target = "creditCurrencyCode", source = "credit.currencyCode")
    AgreementResponceDto agreementToDto(CreditEntity credit, PaymentScheduleEntity payment, CreditAccountEntity debt);
}
