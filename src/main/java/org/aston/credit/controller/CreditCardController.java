package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditCardRequestDto;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.mapper.CreditCardMapper;
import org.aston.credit.service.CreditCardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credit-cards")
public class CreditCardController {
    private final CreditCardService creditCardService;

    private final CreditCardMapper creditCardMapper;

    @GetMapping
    public List<CreditCardEntity> getAll() {
        return creditCardService.getAll();
    }

    @PatchMapping
    public void block(@RequestBody CreditCardRequestDto creditCardDto) {
        final CreditCardEntity creditCardEntity = creditCardMapper.toEntity(creditCardDto);
        creditCardService.block(creditCardEntity);
    }
}
