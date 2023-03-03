package org.aston.credit.service;

import org.aston.credit.entity.CreditEntity;
import org.aston.credit.helper.CreditHelper;
import org.aston.credit.repository.CreditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditServiceTest {
    @InjectMocks
    private CreditService creditService;

    @Mock
    private CreditRepository creditRepository;

    @Test
    void getAll() {
        creditService.getAll();
        Mockito.verify(creditRepository, Mockito.times(1)).findAll();
    }

    @Test
    void schedule() {
        UUID clientId = CreditHelper.CLIENT_ID;
        long creditId = CreditHelper.CREDIT_ID;
        CreditEntity credit = CreditHelper.getCredit();

        when(creditRepository.getReferenceById(creditId)).thenReturn(credit);

        assertThat(creditService.schedule(clientId, creditId)).isEqualTo(credit);
    }
}