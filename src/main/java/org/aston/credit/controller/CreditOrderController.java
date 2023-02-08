package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.service.CreditOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit-order")
public class CreditOrderController {
    private final CreditOrderService creditOrderService;

    @GetMapping
    public List<CreditOrderEntity> getAll() {
        return creditOrderService.getAll();
    }
}
