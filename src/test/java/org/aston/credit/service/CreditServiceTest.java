package org.aston.credit.service;

import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.entity.PaymentScheduleEntity;
import org.aston.credit.exception.ForbiddenException;
import org.aston.credit.helper.CreditHelper;
import org.aston.credit.repository.CreditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditServiceTest {
    @InjectMocks
    private CreditService creditService;
    @Mock
    private CreditRepository creditRepository;
    private final UUID clientId = CreditHelper.CLIENT_ID;
    private final long creditId = CreditHelper.CREDIT_ID;
    private final long agreementId = CreditHelper.AGREEMENT_ID;
    private final CreditEntity credit = CreditHelper.getCredit();
    private final List<PaymentScheduleEntity> schedule = CreditHelper.getPayments();
    private final CreditAccountEntity creditAccount = CreditHelper.getCreditAccount();

    @Test
    void schedule() {
        when(creditRepository.getReferenceById(creditId)).thenReturn(credit);
        assertThat(creditService.schedule(clientId, creditId)).isEqualTo(credit);
    }

    @Test
    void credit() {
        when(creditRepository.findByCreditAgreementId(agreementId)).thenReturn(credit);
        assertThat(creditService.credit(agreementId)).isEqualTo(credit);
    }

    @Test
    void payment() {
        assertThat(creditService.payment(schedule)).isEqualTo(schedule.get(1));
    }

    @Test
    void debt() {
        assertThat(creditService.debt(creditAccount)).isEqualTo(creditAccount);
    }

    @Test
    void scheduleAfter() {
        assertThat(creditService.scheduleAfter(schedule)).isEqualTo(List.of(schedule.get(1)));
    }

    @Test
    void scheduleBefore() {
        assertThat(creditService.scheduleBefore(schedule)).isEqualTo(List.of(schedule.get(0)));
    }

    @Test
    void throwExceptionIfClientIdNotEquals() {
        when(creditRepository.getReferenceById(creditId)).thenReturn(credit);
        assertThrows(ForbiddenException.class,
                () -> creditService.schedule(UUID.fromString("0799f8b8-729d-0000-b1ba-5e64f88f6d03"), creditId));
    }
}