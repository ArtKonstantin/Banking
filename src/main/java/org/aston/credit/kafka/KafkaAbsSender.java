package org.aston.credit.kafka;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abs")
public class KafkaAbsSender {
    private final KafkaAbsService kafkaAbsService;
    @Value("${spring.kafka.topics.tp1}")
    private String topic;

    @PatchMapping("/block")
    public void send(KafkaCreditCardDto kafkaCreditCardDto) {
        kafkaAbsService.send(topic, kafkaCreditCardDto);
    }
}
