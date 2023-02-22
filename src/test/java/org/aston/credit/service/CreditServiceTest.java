package org.aston.credit.service;

import org.aston.credit.repository.CreditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
}