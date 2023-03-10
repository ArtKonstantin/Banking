package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.repository.CreditRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CreditServiceTest {

    @InjectMocks
    private CreditService creditService;

    @Mock
    private CreditRepository creditRepository;

    @Test
    void getAll() {
        creditService.getAll();
        Mockito.verify(creditRepository, Mockito.times(1)).findAll();
    }

    @Test
    void whenGetInformation_thenReturnEntity() {
        CreditEntity expected = new CreditEntity();
        Mockito.when(creditRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(expected));
        CreditEntity entity = creditService.getInformation(1L);
        Assertions.assertEquals(expected, entity);
        Mockito.verify(creditRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }

    @Test
    void whenGetInformation_thenThrowException() {
        Mockito.when(creditRepository.findById(Mockito.anyLong())).thenThrow(NoSuchElementException.class);
        Assertions.assertThrows(EntityNotFoundException.class, () -> creditService.getInformation(1L));
        Mockito.verify(creditRepository, Mockito.times(1)).findById(Mockito.anyLong());
    }
}