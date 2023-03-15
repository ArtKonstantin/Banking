package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditCardRequestDto;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.mapper.CreditCardMapper;
import org.aston.credit.service.CreditCardService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credit-cards")
public class CreditCardController {
    private final CreditCardService creditCardService;

    private final CreditCardMapper creditCardMapper;

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
}
