package org.aston.credit.service;

import org.aston.credit.repository.CreditAccountRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreditAccountServiceTest {
    @Autowired
    private CreditAccountService creditAccountService;

    @MockBean
    private CreditAccountRepository creditAccountRepository;

    @Test
    void getAll() {
        creditAccountService.getAll();
        Mockito.verify(creditAccountRepository, Mockito.times(1)).findAll();
    }
}