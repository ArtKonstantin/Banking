package org.aston.credit.kafka;

import lombok.extern.slf4j.Slf4j;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.dto.KafkaPinCodeDto;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@EnableKafka
@Service
@Slf4j
public class CreditCardEventListener {

    @KafkaListener(topics = "${spring.kafka.topics.tp2}")
    public void msgListener(KafkaCreditCardDto msg) {
        log.info("Got event from Credit service: {}", msg);
    }

    @KafkaListener(topics = "${spring.kafka.topics.tp3}")
    public void msgPinListener(KafkaPinCodeDto msg) {
        log.info("Got event from Credit service: {}", msg);
    }

}
