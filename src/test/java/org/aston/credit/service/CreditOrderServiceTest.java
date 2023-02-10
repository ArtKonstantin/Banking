package org.aston.credit.service;

import org.aston.credit.repository.CreditOrderRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreditOrderServiceTest {
    @Autowired
    private CreditOrderService creditOrderService;

    @MockBean
    private CreditOrderRepository creditOrderRepository;

    @Test
    void getAll() {
        creditOrderService.getAll();
        Mockito.verify(creditOrderRepository, Mockito.times(1)).findAll();

    }
}