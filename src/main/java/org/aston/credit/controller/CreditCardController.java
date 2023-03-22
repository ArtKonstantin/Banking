package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditCardRequestDto;
import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.mapper.CreditCardMapper;
import org.aston.credit.service.CreditCardService;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableKafka
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credit-cards")
public class CreditCardController {
    private final CreditCardService creditCardService;

    private final CreditCardMapper creditCardMapper;
    public static final String MASTER_TO_CREDIT_TOPIC = "master_to_credit_update_status_card";

    @PatchMapping
    public void block(@RequestBody CreditCardRequestDto creditCardDto) {
        final CreditCardEntity creditCardEntity = creditCardMapper.toEntity(creditCardDto);
        creditCardService.block(creditCardEntity);
    }

    @PatchMapping("/limit")
    public void limit(@RequestBody CreditCardRequestDto creditCardDto) {
        final CreditCardEntity creditCardEntity = creditCardMapper.toEntity(creditCardDto);
        creditCardService.limit(creditCardEntity);
    }

    @PostMapping("/code")
    public void pin(@RequestBody CreditCardRequestDto creditCardDto) {
        final CreditCardEntity creditCardEntity = creditCardMapper.toEntity(creditCardDto);
        creditCardService.pin(creditCardEntity);
    }

    @KafkaListener(topics = MASTER_TO_CREDIT_TOPIC)
    public void msgListener(KafkaCreditCardDto msg) {
        final CreditCardEntity creditCardEntity = creditCardMapper.toEntityByKafka(msg);
        creditCardService.blockByKafka(creditCardEntity);
    }
}
