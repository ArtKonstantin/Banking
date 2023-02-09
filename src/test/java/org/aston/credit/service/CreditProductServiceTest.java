package org.aston.credit.service;

import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.repository.CreditProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CreditProductServiceTest {
    @Autowired
    private CreditProductService creditProductService;

    @MockBean
    private CreditProductRepository creditProductRepository;

    @Test
    public void create() {
        final CreditProductEntity creditProduct = new CreditProductEntity();
        creditProductService.create(creditProduct);
        Mockito.verify(creditProductRepository, Mockito.times(1)).save(creditProduct);
    }

    @Test
    public void getAll() {
        creditProductService.getAll();
        Mockito.verify(creditProductRepository, Mockito.times(1)).findAll();
    }

    @Test
    public void getById() {
        long id = 1;
        creditProductService.getById(id);
        Mockito.verify(creditProductRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void update() {
        final CreditProductEntity creditProduct = new CreditProductEntity();
        creditProductService.update(creditProduct);
        Mockito.verify(creditProductRepository, Mockito.times(1)).save(creditProduct);
    }

    @Test
    public void removeById() {
        long id = 1;
        creditProductService.removeById(id);
        Mockito.verify(creditProductRepository, Mockito.times(1)).deleteById(id);
    }
}