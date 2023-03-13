package org.aston.credit.service;

import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.helper.CreditCardHelper;
import org.aston.credit.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceTest {
    @InjectMocks
    private CreditCardService creditCardService;
    @Mock
    private CreditCardService cardService;
    @Mock
    private CreditCardRepository creditCardRepository;
    private static CreditCardEntity creditCard = CreditCardHelper.getCreditCard();

    @Test
    void block() {
        cardService.block(creditCard);

        verify(cardService, times(1)).block(creditCard);
    }

    @Test
    void pin() {
        cardService.pin(creditCard);

        verify(cardService, times(1)).pin(creditCard);
    }

    @Test
    void limit() {
        cardService.limit(creditCard);

        verify(cardService, times(1)).limit(creditCard);
    }

    @Test
    void check() {
        when(creditCardRepository.findByCardNumber(creditCard.getCardNumber())).thenReturn(creditCard);

        assertThat(creditCardService.check(creditCard)).isEqualTo(creditCard);
    }
}