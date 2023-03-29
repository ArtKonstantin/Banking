package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaAbsService {
    private final KafkaTemplate<String, KafkaCreditCardDto> kafkaTemplate;

    public void send(String topic, KafkaCreditCardDto kafkaCreditCardDto) {
        kafkaTemplate.send(topic, kafkaCreditCardDto);
    }
}
