package org.aston.credit.service;

import org.aston.credit.repository.CreditRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CreditServiceTest {
    @Autowired
    private CreditService creditService;

    @MockBean
    private CreditRepository creditRepository;

    @Test
    void getAll() {
        creditService.getAll();
        Mockito.verify(creditRepository, Mockito.times(1)).findAll();
    }
}