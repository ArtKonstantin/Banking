package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.service.CreditService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit")
public class CreditController {
    private final CreditService creditService;

    @GetMapping
    public List<CreditEntity> getAll() {
        return creditService.getAll();
    }
}
