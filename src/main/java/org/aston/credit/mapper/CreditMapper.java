package org.aston.credit.mapper;

import org.aston.credit.dto.ScheduleResponseDto;
import org.aston.credit.entity.CreditEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CreditMapper {

    @Mapping(target = "accountNumber", source = "creditAccount.accountNumber")
    @Mapping(target = "agreementId", source = "creditAgreement.id")
    @Mapping(target = "currentPrincipalAmount", source = "creditAccount.principalDebt")
    @Mapping(target = "currentInterestAmount", source = "creditAccount.interestDebt")
    @Mapping(target = "payments", source = "creditAccount.paymentSchedule")
    ScheduleResponseDto toDto(CreditEntity credit);
}
