package org.aston.credit.service;

import org.aston.credit.repository.CreditAgreementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditAgreementServiceTest {
    @InjectMocks
    private CreditAgreementService creditAgreementService;

    @Mock
    private CreditAgreementRepository creditAgreementRepository;

    @Test
    void getAll() {
        creditAgreementService.getAll();
        Mockito.verify(creditAgreementRepository, Mockito.times(1)).findAll();
    }
}