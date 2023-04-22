package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.dto.KafkaPinCodeDto;
import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.enums.CardStatusEnum;
import org.aston.credit.exception.BadCardBalanceException;
import org.aston.credit.exception.BadCardStatusException;
import org.aston.credit.exception.BadRequestException;
import org.aston.credit.mapper.CreditCardMapper;
import org.aston.credit.repository.CreditCardRepository;
import org.aston.credit.repository.CreditOrderRepository;
import org.aston.credit.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditCardService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;
    @Value("${spring.kafka.topics.update-status-in}")
    private String topic;
    @Value("${spring.kafka.topics.update-pin}")
    private String topicPin;
    private final CreditOrderRepository creditOrderRepository;
    private final CreditRepository creditRepository;

    public void block(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = getById(creditCardEntity.getCardNumber());

        if (creditCard.getCardStatus().equals(creditCardEntity.getCardStatus())) {
            throw new BadRequestException();
        }

        creditCard.setCardStatus(creditCardEntity.getCardStatus());
        creditCardRepository.save(creditCard);

        final KafkaCreditCardDto kafkaCreditCardDto = creditCardMapper.toKafkaCreditCardDto(creditCard);
        kafkaTemplate.send(topic, kafkaCreditCardDto);
    }

    public void blockFromAbs(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = getById(creditCardEntity.getCardNumber());

        if (!creditCard.getCardStatus().equals(creditCardEntity.getCardStatus())) {
            creditCard.setCardStatus(creditCardEntity.getCardStatus());
            creditCardRepository.save(creditCard);
        }
        else throw new BadCardStatusException();
    }

    public void pin(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = getById(creditCardEntity.getCardNumber());

        creditCard.setPin(creditCardEntity.getPin());
        creditCardRepository.save(creditCard);

        final KafkaPinCodeDto kafkaPinCodeDto = creditCardMapper.toKafkaPinCodeDto(creditCard);
        kafkaTemplate.send(topicPin, kafkaPinCodeDto);
    }

    public void limit(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = getById(creditCardEntity.getCardNumber());

        creditCard.setTransactionLimit(creditCardEntity.getTransactionLimit());
        creditCardRepository.save(creditCard);
    }

    public CreditCardEntity getById(String cardId) {
        final CreditCardEntity creditCard = creditCardRepository.findByCardNumber(cardId);

        if (creditCard == null) {
            throw new EntityNotFoundException();
        }

        return creditCard;
    }

    public CreditCardEntity findCardById(UUID cardId) {
        final Optional<CreditCardEntity> creditCard = creditCardRepository.findById(cardId);

        if (creditCard.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return creditCard.get();
    }

    public void deleteCardById(UUID cardId) {
        final Optional<CreditCardEntity> creditCard = creditCardRepository.findById(cardId);

        if (creditCard.isEmpty()) {
            throw new EntityNotFoundException();
        }

        final CreditCardEntity creditCardEntity = creditCard.get();

        if (creditCardEntity.getLimit().compareTo(BigDecimal.valueOf(creditCardEntity.getCardBalance())) != 0) {
            throw new BadCardBalanceException();
        }

        creditCardEntity.setCardStatus(CardStatusEnum.DELETED);
        creditCardRepository.save(creditCardEntity);
    }

    public List<CreditCardEntity> getAllByClientId(UUID clientId) {
        List<CreditOrderEntity> creditOrders = creditOrderRepository.findAllByClientId(clientId);
        List<CreditAccountEntity> creditAccounts = new ArrayList<>();
        List<CreditCardEntity> creditCards = new ArrayList<>();

        if (creditOrders.isEmpty()) {
            throw new EntityNotFoundException();
        }

        for (CreditOrderEntity creditOrder : creditOrders) {
            CreditEntity credit = creditRepository.findByCreditOrder(creditOrder);
            if (credit != null) {
                creditAccounts.add(credit.getCreditAccount());
            }
        }

        for (CreditAccountEntity creditAccount : creditAccounts) {
            final List<CreditCardEntity> allByCreditAccount = creditCardRepository.findAllByCreditAccount(creditAccount);
            creditCards.addAll(allByCreditAccount);
        }

        return creditCards;
    }
}
