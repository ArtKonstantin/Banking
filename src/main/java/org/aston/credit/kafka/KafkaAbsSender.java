package org.aston.credit.kafka;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abs")
public class KafkaAbsSender {
    public static final String MASTER_TO_CREDIT_TOPIC = "master_to_credit_update_status_card";
    private final KafkaTemplate<String, KafkaCreditCardDto> kafkaTemplate;

    @PatchMapping("/block")
    public void send(KafkaCreditCardDto kafkaCreditCardDto) {
        kafkaTemplate.send(MASTER_TO_CREDIT_TOPIC, kafkaCreditCardDto);
    }
}
