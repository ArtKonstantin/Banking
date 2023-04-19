package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.dto.KafkaPinCodeDto;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.exception.BadRequestException;
import org.aston.credit.mapper.CreditCardMapper;
import org.aston.credit.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditCardService {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final CreditCardRepository creditCardRepository;
    private final CreditOrderService creditOrderService;
    private final CreditCardMapper creditCardMapper;
    @Value("${spring.kafka.topics.update-status-in}")
    private String topic;
    @Value("${spring.kafka.topics.update-pin}")
    private String topicPin;

    public void block(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = check(creditCardEntity);

        if (creditCard.getCardStatus().equals(creditCardEntity.getCardStatus())) {
            throw new BadRequestException();
        }

        creditCard.setCardStatus(creditCardEntity.getCardStatus());
        creditCardRepository.save(creditCard);

        final KafkaCreditCardDto kafkaCreditCardDto = creditCardMapper.toKafkaCreditCardDto(creditCard);
        kafkaTemplate.send(topic, kafkaCreditCardDto);
    }

    public void blockFromAbs(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = check(creditCardEntity);

        if (!creditCard.getCardStatus().equals(creditCardEntity.getCardStatus())) {
            creditCard.setCardStatus(creditCardEntity.getCardStatus());
            creditCardRepository.save(creditCard);
        }
    }

    public void pin(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = check(creditCardEntity);

        creditCard.setPin(creditCardEntity.getPin());
        creditCardRepository.save(creditCard);

        final KafkaPinCodeDto kafkaPinCodeDto = creditCardMapper.toKafkaPinCodeDto(creditCard);
        kafkaTemplate.send(topicPin, kafkaPinCodeDto);
    }

    public void limit(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = check(creditCardEntity);

        creditCard.setTransactionLimit(creditCardEntity.getTransactionLimit());
        creditCardRepository.save(creditCard);
    }

    public CreditCardEntity check(CreditCardEntity creditCardEntity) {
        final CreditCardEntity creditCard = creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber());

        if (creditCard == null) {
            throw new EntityNotFoundException();
        }

        return creditCard;
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
}
