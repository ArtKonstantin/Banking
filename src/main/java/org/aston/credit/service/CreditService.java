package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.entity.CreditAgreementEntity;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.entity.PaymentScheduleEntity;
import org.aston.credit.exception.ForbiddenException;
import org.aston.credit.repository.CreditRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    public CreditEntity agreement(long agreementId) {
        final CreditEntity credit = creditRepository.findByCreditAgreementId(agreementId);
//        CreditEntity agreement = new CreditEntity();
//        CreditAccountEntity account = new CreditAccountEntity();
//
//        final List<PaymentScheduleEntity> paymentSchedule = credit.getCreditAccount().getPaymentSchedule();
//        PaymentScheduleEntity payment = new PaymentScheduleEntity();
//        Date date = new Date();
//        for (PaymentScheduleEntity pay : paymentSchedule) {
//            if (pay.getPaymentDate().after(date)) {
//                payment = pay;
//                break;
//            }
//        }
//
//        agreement.setCreditAgreement(credit.getCreditAgreement());
//        agreement.setCurrencyCode(credit.getCurrencyCode());
//        account.setPaymentSchedule((List<PaymentScheduleEntity>) payment);
//        agreement.setCreditAccount(account);
//

        return credit;
    }
}
