package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.service.CreditCardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit-card")
public class CreditCardController {
    private final CreditCardService creditCardService;

    @GetMapping
    public List<CreditCardEntity> getAll() {
        return creditCardService.getAll();
    }
}
