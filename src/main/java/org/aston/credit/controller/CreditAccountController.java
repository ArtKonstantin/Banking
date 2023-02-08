package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.service.CreditAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit-account")
public class CreditAccountController {
    private final CreditAccountService creditAccountService;

    @GetMapping
    public List<CreditAccountEntity> getAll() {
        return creditAccountService.getAll();
    }
}
