package org.aston.credit.service;

import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.helper.CreditOrderHelper;
import org.aston.credit.repository.CreditProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditProductServiceTest {
    @InjectMocks
    private CreditProductService creditProductService;

    @Mock
    private CreditProductRepository creditProductRepository;

    @Test
    void create() {
        final CreditProductEntity creditProduct = new CreditProductEntity();
        creditProductService.save(creditProduct);
        Mockito.verify(creditProductRepository, Mockito.times(1)).save(creditProduct);
    }

    @Test
    void getAll() {
        creditProductService.getAll();
        Mockito.verify(creditProductRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getById() {
        creditProductService.getById(CreditOrderHelper.ID_TEST);
        Mockito.verify(creditProductRepository, Mockito.times(1)).getReferenceById(CreditOrderHelper.ID_TEST);
    }

    @Test
    void removeById() {
        creditProductService.removeById(CreditOrderHelper.ID_TEST);
        Mockito.verify(creditProductRepository, Mockito.times(1)).deleteById(CreditOrderHelper.ID_TEST);
    }
}