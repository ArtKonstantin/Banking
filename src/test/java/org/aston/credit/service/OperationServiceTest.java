package org.aston.credit.service;

import org.aston.credit.repository.OperationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OperationServiceTest {
    @Autowired
    private OperationService operationService;

    @MockBean
    private OperationRepository operationRepository;

    @Test
    void getAll() {
        operationService.getAll();
        Mockito.verify(operationRepository, Mockito.times(1)).findAll();
    }
}