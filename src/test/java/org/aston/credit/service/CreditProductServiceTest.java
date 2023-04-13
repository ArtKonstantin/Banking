package org.aston.credit.service;

import org.aston.credit.repository.CreditProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditProductServiceTest {
    public static final long ID_TEST = 1;
    @InjectMocks
    private CreditProductService creditProductService;
    @Mock
    private CreditProductRepository creditProductRepository;

    @Test
    void getAllActive() {
        creditProductService.getAllActive();
        Mockito.verify(creditProductRepository, Mockito.times(1)).findAllByProductIsActiveIsTrue();
    }

    @Test
    void getById() {
        creditProductService.getById(ID_TEST);
        Mockito.verify(creditProductRepository, Mockito.times(1)).getReferenceById(ID_TEST);
    }
}