package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.dto.KafkaPinCodeDto;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.enums.CardStatusEnum;
import org.aston.credit.exception.BadCardBalanceException;
import org.aston.credit.exception.BadCardStatusException;
import org.aston.credit.exception.BadRequestException;
import org.aston.credit.helper.CreditCardHelper;
import org.aston.credit.helper.CreditHelper;
import org.aston.credit.helper.CreditOrderHelper;
import org.aston.credit.mapper.CreditCardMapper;
import org.aston.credit.repository.CreditCardRepository;
import org.aston.credit.repository.CreditOrderRepository;
import org.aston.credit.repository.CreditRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
    @Mock
    private CreditOrderRepository creditOrderRepository;
    @Mock
    private CreditRepository creditRepository;
    @Mock
    private CreditCardMapper creditCardMapper;
    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    void block() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        CreditCardEntity creditCardEntity = mock(CreditCardEntity.class);
        KafkaCreditCardDto kafkaCreditCardDto = CreditCardHelper.getKafkaCreditCardDto();

        when(creditCardEntity.getCardStatus()).thenReturn(CardStatusEnum.ACTIVE);
        when(creditCardEntity.getCardNumber()).thenReturn("1234567891234567");
        when(creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber())).thenReturn(creditCard);
        when(creditCardMapper.toKafkaCreditCardDto(creditCard)).thenReturn(kafkaCreditCardDto);

        cardService.block(creditCardEntity);
        verify(creditCardRepository, times(1)).save(creditCard);
        verify(kafkaTemplate, times(1)).send(null, kafkaCreditCardDto);
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
        KafkaPinCodeDto kafkaPinCodeDto = CreditCardHelper.getKafkaPiCodeDto();
        CreditCardEntity creditCardEntity = mock(CreditCardEntity.class);

        when(creditCardEntity.getPin()).thenReturn("4321");
        when(creditCardEntity.getCardNumber()).thenReturn("1234567891234567");
        when(creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber())).thenReturn(creditCard);
        when(creditCardMapper.toKafkaPinCodeDto(creditCard)).thenReturn(kafkaPinCodeDto);

        cardService.pin(creditCardEntity);
        verify(creditCardRepository, times(1)).save(creditCard);
        verify(kafkaTemplate, times(1)).send(null, kafkaPinCodeDto);
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
    void getById() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        String cardId = "1234567891234567";

        when(creditCardRepository.findByCardNumber(cardId)).thenReturn(creditCard);

        assertThat(cardService.getById(cardId)).isEqualTo(creditCard);
    }

    @Test
    void throwException_ifCreditCardIsNull() {
        String cardId = "1234567891234567";

        when(creditCardRepository.findByCardNumber(cardId)).thenReturn(null);

        assertThrows(EntityNotFoundException.class,
                () -> cardService.getById(cardId));
    }

    @Test
    void findCardById() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        UUID cardId = UUID.fromString("f34f8980-0965-4132-8947-deb1e6170b2c");

        when(creditCardRepository.findById(cardId)).thenReturn(Optional.of(creditCard));

        assertThat(cardService.findCardById(cardId)).isEqualTo(creditCard);
    }

    @Test
    void throwException_ifCreditCardIdIsNull() {
        UUID cardId = UUID.fromString("f34f8980-0965-4132-8947-deb1e6170b2c");

        when(creditCardRepository.findById(cardId)).thenReturn(Optional.ofNullable(null));

        assertThrows(EntityNotFoundException.class,
                () -> cardService.findCardById(cardId));
        assertThrows(EntityNotFoundException.class,
                () -> cardService.deleteCardById(cardId));
    }

    @Test
    void blockFromAbs() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        CreditCardEntity creditCardEntity = mock(CreditCardEntity.class);

        when(creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber())).thenReturn(creditCard);

        cardService.blockFromAbs(creditCardEntity);
        verify(creditCardRepository, times(1)).save(creditCard);
    }

    @Test
    void throwException_ifCardsStatusIsEquals() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        CreditCardEntity creditCardEntity = mock(CreditCardEntity.class);

        when(creditCardEntity.getCardStatus()).thenReturn(CardStatusEnum.BLOCKED);
        when(creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber())).thenReturn(creditCard);

        assertThrows(BadCardStatusException.class,
                () -> cardService.blockFromAbs(creditCardEntity));
    }

    @Test
    void deleteCardById() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        UUID cardId = UUID.fromString("f34f8980-0965-4132-8947-deb1e6170b2c");

        when(creditCardRepository.findById(cardId)).thenReturn(Optional.of(creditCard));

        cardService.deleteCardById(cardId);
        verify(creditCardRepository, times(1)).save(creditCard);
    }

    @Test
    void throwException_ifCardsBalanceIsNotEquals() {
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        UUID cardId = UUID.fromString("f34f8980-0965-4132-8947-deb1e6170b2c");
        creditCard.setCardBalance(100);

        when(creditCardRepository.findById(cardId)).thenReturn(Optional.of(creditCard));

        assertThrows(BadCardBalanceException.class,
                () -> cardService.deleteCardById(cardId));
    }

    @Test
    void getAllByClientId() {
        UUID clientId = UUID.fromString("0799f8b8-729d-4818-b1ba-5e64f88f6d03");
        List<CreditOrderEntity> creditOrders = new ArrayList<>();
        creditOrders.add(CreditOrderHelper.getCreditOrder());

        List<CreditCardEntity> creditCards = new ArrayList<>();
        CreditCardEntity creditCard = CreditCardHelper.getCreditCard();
        creditCards.add(creditCard);

        CreditEntity credit = CreditHelper.getCredit();

        when(creditOrderRepository.findAllByClientId(clientId)).thenReturn(creditOrders);
        when(creditRepository.findByCreditOrder(creditOrders.get(0))).thenReturn(credit);
        when(creditCardRepository.findAllByCreditAccount(credit.getCreditAccount())).thenReturn(creditCards);

        assertThat(cardService.getAllByClientId(clientId)).isEqualTo(creditCards);
    }

    @Test
    void throwException_ifCreditOrderNotFound() {
        UUID clientId = UUID.fromString("0799f8b8-729d-4818-b1ba-5e64f88f6d03");
        List<CreditOrderEntity> creditOrders = new ArrayList<>();

        when(creditOrderRepository.findAllByClientId(clientId)).thenReturn(creditOrders);

        assertThrows(EntityNotFoundException.class,
                () -> cardService.getAllByClientId(clientId));
    }
}