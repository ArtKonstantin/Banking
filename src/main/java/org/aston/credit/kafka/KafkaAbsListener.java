package org.aston.credit.kafka;

import org.aston.credit.dto.KafkaCreditCardDto;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@EnableKafka
@Component
public class KafkaAbsListener {

    @KafkaListener(topics = "credit_to_master_update_status_card")
    public void msgListener(KafkaCreditCardDto msg) {
        System.out.println(msg);
    }
}
