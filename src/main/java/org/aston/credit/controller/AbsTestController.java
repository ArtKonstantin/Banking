package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.service.KafkaAbsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/abs")
public class AbsTestController {
    private final KafkaAbsService kafkaAbsService;
    @Value("${spring.kafka.topics.update-status-from}")
    private String topic;

    /**
     * Имитация отправки запроса на блокировку кредитной карты со стороны АБС
     *
     * @param kafkaCreditCardDto
     */
    @PatchMapping("/block")
    public void send(KafkaCreditCardDto kafkaCreditCardDto) {
        kafkaAbsService.send(topic, kafkaCreditCardDto);
    }
}
