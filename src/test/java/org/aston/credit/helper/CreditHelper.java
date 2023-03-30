package org.aston.credit.helper;

import org.aston.credit.dto.responses.ScheduleResponseDto;
import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.entity.CreditAgreementEntity;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.entity.PaymentScheduleEntity;
import org.aston.credit.entity.enums.CreditStatusEnum;
import org.aston.credit.entity.enums.CreditTypeEnum;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreditHelper {
    public static final UUID CLIENT_ID = UUID.fromString("0799f8b8-729d-4818-b1ba-5e64f88f6d03");
    public static final long CREDIT_ID = 1;
    public static final long AGREEMENT_ID = 1;

    public static List<PaymentScheduleEntity> getPayments() {
        List<PaymentScheduleEntity> payments = new ArrayList<>();
        payments.add(new PaymentScheduleEntity(1,
                null,
                LocalDate.of(2023, 3, 1),
                BigDecimal.valueOf(146274.27),
                BigDecimal.valueOf(10356.16)));
        payments.add(new PaymentScheduleEntity(1,
                null,
                LocalDate.of(2023, 4, 1),
                BigDecimal.valueOf(147028.17),
                BigDecimal.valueOf(9602.26)));

        return payments;
    }

    private static CreditAgreementEntity getCreditAgreement() {
        CreditAgreementEntity creditAgreement = new CreditAgreementEntity();
        creditAgreement.setId(1);
        return creditAgreement;
    }

    public static CreditAccountEntity getCreditAccount() {
        CreditAccountEntity creditAccount = new CreditAccountEntity();
        creditAccount.setAccountNumber("10000000000000000001");
        creditAccount.setPrincipalDebt(BigDecimal.valueOf(980000.00));
        creditAccount.setInterestDebt(BigDecimal.valueOf(39010.00));
        creditAccount.setPaymentSchedule(getPayments());
        return creditAccount;
    }

    public static CreditEntity getCredit() {
        CreditEntity credit = new CreditEntity();
        credit.setId(1);
        credit.setCreditAgreement(getCreditAgreement());
        credit.setCreditOrder(CreditOrderHelper.getCreditOrder());
        credit.setType(CreditTypeEnum.CONSUMER_CREDIT);
        credit.setCreditLimit(BigDecimal.valueOf(900000.00));
        credit.setCurrencyCode("RUB");
        credit.setInterestRate(BigDecimal.valueOf(0.15));
        credit.setPersonalGuarantees(true);
        credit.setGracePeriodMonths(0);
        credit.setCreditStatus(CreditStatusEnum.ACTIVE);
        credit.setCreditAccount(getCreditAccount());
        return credit;
    }

    public static ScheduleResponseDto getScheduleDto() {
        return ScheduleResponseDto.builder().
                accountNumber("10000000000000000001").
                agreementId(1).
                currentPrincipalAmount(BigDecimal.valueOf(980000.00)).
                currentInterestAmount(BigDecimal.valueOf(39010.00)).
                payments(getPayments()).
                build();
    }
}
