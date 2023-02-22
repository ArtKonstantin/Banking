package org.aston.credit.service;

import org.aston.credit.repository.CreditAccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditAccountServiceTest {
    @InjectMocks
    private CreditAccountService creditAccountService;

    @Mock
    private CreditAccountRepository creditAccountRepository;

    @Test
    void getAll() {
        creditAccountService.getAll();
        Mockito.verify(creditAccountRepository, Mockito.times(1)).findAll();
    }
}