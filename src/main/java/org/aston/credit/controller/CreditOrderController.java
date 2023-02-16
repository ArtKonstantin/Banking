package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.NewCreditOrderRequestDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.service.CreditOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit-order")
public class CreditOrderController {
    private final CreditOrderService creditOrderService;

    @GetMapping
    public List<CreditOrderEntity> getAll() {
        return creditOrderService.getAll();
    }

    @PostMapping
    public void create(@RequestHeader UUID clientId, @RequestBody NewCreditOrderRequestDto newCreditOrderRequestDto){
        creditOrderService.create(clientId, newCreditOrderRequestDto);
    }
}
