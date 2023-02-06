package org.aston.credit.controller;

import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.repository.CreditAccountRepository;
import org.aston.credit.service.CreditAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/credit-account")
public class CreditAccountController {
    private final CreditAccountService creditAccountService;

    @Autowired
    public CreditAccountController(CreditAccountService creditAccountService) {
        this.creditAccountService = creditAccountService;
    }

    @GetMapping
    public List<CreditAccountEntity> getAll() {
        return creditAccountService.getAll();
    }
}
