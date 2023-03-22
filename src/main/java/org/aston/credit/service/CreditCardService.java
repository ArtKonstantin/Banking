package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.exception.BadRequestException;
import org.aston.credit.mapper.CreditCardMapper;
import org.aston.credit.repository.CreditCardRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    public static final String CREDIT_TO_MASTER_TOPIC = "credit_to_master_update_status_card";
    private final KafkaTemplate<String, KafkaCreditCardDto> kafkaTemplate;
    private final CreditCardRepository creditCardRepository;
    private final CreditCardMapper creditCardMapper;

    public void block(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = check(creditCardEntity);

        if (creditCard.getCardStatus().equals(creditCardEntity.getCardStatus())) {
            throw new BadRequestException();
        }

        creditCard.setCardStatus(creditCardEntity.getCardStatus());
        creditCardRepository.save(creditCard);

        final KafkaCreditCardDto kafkaCreditCardDto = creditCardMapper.toKafkaDto(creditCard);
        kafkaTemplate.send(CREDIT_TO_MASTER_TOPIC, kafkaCreditCardDto);
    }

    public void blockByKafka(CreditCardEntity creditCardEntity) {
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
