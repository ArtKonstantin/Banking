package org.aston.credit.service;

import org.aston.credit.repository.OperationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OperationServiceTest {
    @InjectMocks
    private OperationService operationService;

    @Mock
    private OperationRepository operationRepository;

    @Test
    void getAll() {
        operationService.getAll();
        Mockito.verify(operationRepository, Mockito.times(1)).findAll();
    }
}