package org.aston.credit.service;

import org.aston.credit.repository.PaymentScheduleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentScheduleServiceTest {
    @Autowired
    private PaymentScheduleService paymentScheduleService;

    @MockBean
    private PaymentScheduleRepository paymentScheduleRepository;

    @Test
    void getAll() {
        paymentScheduleService.getAll();
        Mockito.verify(paymentScheduleRepository, Mockito.times(1)).findAll();
    }
}