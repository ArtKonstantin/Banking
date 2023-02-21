package org.aston.credit.service;

import org.aston.credit.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CreditCardServiceTest {
    @Autowired
    private CreditCardService creditCardService;

    @MockBean
    private CreditCardRepository creditCardRepository;

    @Test
    void getAll() {
        creditCardService.getAll();
        Mockito.verify(creditCardRepository, Mockito.times(1)).findAll();
    }
}