package org.aston.credit.controller;

import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.service.CreditOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/credit-order")
public class CreditOrderController {
    private final CreditOrderService creditOrderService;

    @Autowired
    public CreditOrderController(CreditOrderService creditOrderService) {
        this.creditOrderService = creditOrderService;
    }

    @GetMapping
    public List<CreditOrderEntity> getAll() {
        return creditOrderService.getAll();
    }
}
