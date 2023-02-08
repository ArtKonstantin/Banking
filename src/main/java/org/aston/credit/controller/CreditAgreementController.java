package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditAgreementEntity;
import org.aston.credit.service.CreditAgreementService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit-agreement")
public class CreditAgreementController {
    private final CreditAgreementService creditAgreementService;

    @GetMapping
    public List<CreditAgreementEntity> getAll() {
        return creditAgreementService.getAll();
    }
}
