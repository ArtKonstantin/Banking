package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.entity.PaymentScheduleEntity;
import org.aston.credit.exception.ForbiddenException;
import org.aston.credit.repository.CreditRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditRepository creditRepository;

    public CreditEntity schedule(UUID clientId, long creditId) {
        final CreditEntity credit = creditRepository.getReferenceById(creditId);

        if (!clientId.equals(credit.getCreditOrder().getClientId())) {
            throw new ForbiddenException();
        }

        return credit;
    }

    public CreditEntity credit(long agreementId) {
        return creditRepository.findByCreditAgreementId(agreementId);
    }

    public PaymentScheduleEntity payment(List<PaymentScheduleEntity> schedule) {
        return scheduleAfter(schedule).get(0);
    }

    public CreditAccountEntity debt(CreditAccountEntity creditAccount) {
        BigDecimal principalDebt = creditAccount.getPrincipalDebt();
        BigDecimal interestDebt = creditAccount.getInterestDebt();
        List<PaymentScheduleEntity> schedule = scheduleBefore(creditAccount.getPaymentSchedule());

        for (PaymentScheduleEntity pay : schedule) {
            principalDebt = principalDebt.subtract(pay.getPrincipal());
            interestDebt = interestDebt.subtract(pay.getInterest());
        }

        creditAccount.setPrincipalDebt(principalDebt);
        creditAccount.setInterestDebt(interestDebt);

        return creditAccount;
    }

    public List<PaymentScheduleEntity> scheduleAfter(List<PaymentScheduleEntity> paymentSchedule) {
        List<PaymentScheduleEntity> schedule = new ArrayList<>();
        final LocalDate date = LocalDate.now();

        for (PaymentScheduleEntity pay : paymentSchedule) {
            if (pay.getPaymentDate().isAfter(date)) {
                schedule.add(pay);
            }
        }

        return schedule;
    }

    public List<PaymentScheduleEntity> scheduleBefore(List<PaymentScheduleEntity> paymentSchedule) {
        List<PaymentScheduleEntity> schedule = new ArrayList<>();
        final LocalDate date = LocalDate.now();

        for (PaymentScheduleEntity pay : paymentSchedule) {
            if (pay.getPaymentDate().isBefore(date)) {
                schedule.add(pay);
            }
        }

        return schedule;
    }
}
