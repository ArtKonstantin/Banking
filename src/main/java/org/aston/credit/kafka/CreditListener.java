package org.aston.credit.kafka;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.mapper.CreditCardMapper;
import org.aston.credit.service.CreditCardService;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@EnableKafka
public class CreditListener {

    private final CreditCardService creditCardService;
    private final CreditCardMapper creditCardMapper;

    @KafkaListener(topics = "${spring.kafka.topics.tp1}")
    public void msgListener(KafkaCreditCardDto msg) {
        final CreditCardEntity creditCardEntity = creditCardMapper.toEntityByKafka(msg);
        creditCardService.blockFromAbs(creditCardEntity);
    }
}
