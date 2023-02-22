package org.aston.credit.service;

import org.aston.credit.repository.PaymentScheduleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PaymentScheduleServiceTest {
    @InjectMocks
    private PaymentScheduleService paymentScheduleService;

    @Mock
    private PaymentScheduleRepository paymentScheduleRepository;

    @Test
    void getAll() {
        paymentScheduleService.getAll();
        Mockito.verify(paymentScheduleRepository, Mockito.times(1)).findAll();
    }
}