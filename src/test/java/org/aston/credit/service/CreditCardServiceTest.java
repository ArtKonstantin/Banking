package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import org.aston.credit.entity.enums.CardStatusEnum;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.exception.BadRequestException;
import org.aston.credit.helper.CreditCardHelper;
import org.aston.credit.repository.CreditCardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditCardServiceTest {
    @InjectMocks
    private CreditCardService cardService;
    @Mock
    private CreditCardRepository creditCardRepository;

    @Test
    void block() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        CreditCardEntity creditCardEntity = mock(CreditCardEntity.class);

        when(creditCardEntity.getCardStatus()).thenReturn(CardStatusEnum.ACTIVE);
        when(creditCardEntity.getCardNumber()).thenReturn("1234567891234567");
        when(creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber())).thenReturn(creditCard);

        cardService.block(creditCardEntity);
        verify(creditCardRepository, times(1)).save(creditCard);
    }

    @Test
    void throwException_ifCardStatusIsBlocked() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        CreditCardEntity creditCardEntity = mock(CreditCardEntity.class);

        when(creditCardEntity.getCardStatus()).thenReturn(CardStatusEnum.BLOCKED);
        when(creditCardEntity.getCardNumber()).thenReturn("1234567891234567");
        when(creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber())).thenReturn(creditCard);

        assertThrows(BadRequestException.class,
                () -> cardService.block(creditCardEntity));
    }

    @Test
    void pin() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        CreditCardEntity creditCardEntity = mock(CreditCardEntity.class);

        when(creditCardEntity.getPin()).thenReturn("4321");
        when(creditCardEntity.getCardNumber()).thenReturn("1234567891234567");
        when(creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber())).thenReturn(creditCard);

        cardService.pin(creditCardEntity);
        verify(creditCardRepository, times(1)).save(creditCard);
    }

    @Test
    void limit() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        CreditCardEntity creditCardEntity = mock(CreditCardEntity.class);

        when(creditCardEntity.getTransactionLimit()).thenReturn(10000);
        when(creditCardEntity.getCardNumber()).thenReturn("1234567891234567");
        when(creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber())).thenReturn(creditCard);

        cardService.limit(creditCardEntity);
        verify(creditCardRepository, times(1)).save(creditCard);
    }

    @Test
    void check() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        CreditCardEntity creditCardEntity = mock(CreditCardEntity.class);

        when(creditCardEntity.getCardNumber()).thenReturn("1234567891234567");
        when(creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber())).thenReturn(creditCard);

        assertThat(cardService.check(creditCardEntity)).isEqualTo(creditCard);
    }

    @Test
    void throwException_ifCreditCardIsNull() {
        CreditCardEntity creditCardEntity = mock(CreditCardEntity.class);

        when(creditCardEntity.getCardNumber()).thenReturn("1234567891234567");
        when(creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber())).thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                () -> cardService.check(creditCardEntity));
    }
}