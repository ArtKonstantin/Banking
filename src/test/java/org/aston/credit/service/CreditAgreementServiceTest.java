package org.aston.credit.service;

import org.aston.credit.repository.CreditAgreementRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CreditAgreementServiceTest {
    @Autowired
    private CreditAgreementService creditAgreementService;

    @MockBean
    private CreditAgreementRepository creditAgreementRepository;

    @Test
    void getAll() {
        creditAgreementService.getAll();
        Mockito.verify(creditAgreementRepository, Mockito.times(1)).findAll();
    }
}