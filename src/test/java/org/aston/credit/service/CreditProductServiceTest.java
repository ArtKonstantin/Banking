package org.aston.credit.service;

import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.repository.CreditProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class CreditProductServiceTest {
    public static final long ID_TEST = 1;
    @Autowired
    private CreditProductService creditProductService;

    @MockBean
    private CreditProductRepository creditProductRepository;

    @Test
    void create() {
        final CreditProductEntity creditProduct = new CreditProductEntity();
        creditProductService.create(creditProduct);
        Mockito.verify(creditProductRepository, Mockito.times(1)).save(creditProduct);
    }

    @Test
    void getAll() {
        creditProductService.getAll();
        Mockito.verify(creditProductRepository, Mockito.times(1)).findAll();
    }

    @Test
    void getById() {
        creditProductService.getById(ID_TEST);
        Mockito.verify(creditProductRepository, Mockito.times(1)).findById(ID_TEST);
    }

    @Test
    void update() {
        final CreditProductEntity creditProduct = new CreditProductEntity();
        creditProductService.update(creditProduct);
        Mockito.verify(creditProductRepository, Mockito.times(1)).save(creditProduct);
    }

    @Test
    void removeById() {
        creditProductService.removeById(ID_TEST);
        Mockito.verify(creditProductRepository, Mockito.times(1)).deleteById(ID_TEST);
    }
}