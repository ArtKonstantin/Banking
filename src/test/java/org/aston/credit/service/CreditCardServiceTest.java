package org.aston.credit.service;

import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.helper.CreditCardHelper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceTest {
    @Mock
    private CreditCardService cardService;

    @Test
    void block() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();

        cardService.block(creditCard);

        verify(cardService, times(1)).block(creditCard);
    }
}