package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.exception.BadRequestException;
import org.aston.credit.mapper.CreditCardMapper;
import org.aston.credit.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditCardService {
    private final KafkaTemplate<String, KafkaCreditCardDto> kafkaTemplate;
    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;
    @Value("${spring.kafka.topics.tp2}")
    private String topic;

    public void block(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = check(creditCardEntity);

        if (creditCard.getCardStatus().equals(creditCardEntity.getCardStatus())) {
            throw new BadRequestException();
        }

        creditCard.setCardStatus(creditCardEntity.getCardStatus());
        creditCardRepository.save(creditCard);

        final KafkaCreditCardDto kafkaCreditCardDto = creditCardMapper.toKafkaDto(creditCard);
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
}
