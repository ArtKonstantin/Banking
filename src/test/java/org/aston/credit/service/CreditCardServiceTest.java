package org.aston.credit.service;

import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.helper.CreditCardHelper;
import org.aston.credit.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceTest {
    @InjectMocks
    private CreditCardService creditCardService;

    @Mock
    private CreditCardService cardService;

    @Mock
    private CreditCardRepository creditCardRepository;

    @Test
    void getAll() {
        creditCardService.getAll();
        Mockito.verify(creditCardRepository, Mockito.times(1)).findAll();
    }

    @Test
    void block() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();

        cardService.block(creditCard);

        verify(cardService, times(1)).block(creditCard);
    }
}