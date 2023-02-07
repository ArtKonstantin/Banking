package org.aston.credit.controller;

import org.aston.credit.entity.CreditAgreementEntity;
import org.aston.credit.service.CreditAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/credit-agreement")
public class CreditAgreementController {
    private final CreditAgreementService creditAgreementService;

    @Autowired
    public CreditAgreementController(CreditAgreementService creditAgreementService) {
        this.creditAgreementService = creditAgreementService;
    }

    @GetMapping
    public List<CreditAgreementEntity> getAll() {
        return creditAgreementService.getAll();
    }
}
