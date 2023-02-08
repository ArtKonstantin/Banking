package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.PaymentScheduleEntity;
import org.aston.credit.service.PaymentScheduleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payment-schedule")
public class PaymentScheduleController {
    private final PaymentScheduleService paymentScheduleService;

    @GetMapping
    public List<PaymentScheduleEntity> getAll() {
        return paymentScheduleService.getAll();
    }
}
