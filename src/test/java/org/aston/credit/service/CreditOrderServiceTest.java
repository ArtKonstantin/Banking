package org.aston.credit.service;

import org.aston.credit.repository.CreditOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditOrderServiceTest {
    @InjectMocks
    private CreditOrderService creditOrderService;

    @Mock
    private CreditOrderRepository creditOrderRepository;

    @Test
    void getAll() {
        creditOrderService.getAll();
        Mockito.verify(creditOrderRepository, Mockito.times(1)).findAll();
    }
}