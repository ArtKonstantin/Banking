package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditOrderApprovedDto;
import org.aston.credit.dto.CreditOrderRequestDto;
import org.aston.credit.dto.CreditOrderResponseDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.mapper.CreditOrderMapper;
import org.aston.credit.service.CreditOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/loan-applications")
public class CreditOrderController {
    private final CreditOrderService creditOrderService;
    private final CreditOrderMapper creditOrderMapper;

    // TODO-0: UUID клиента передается в HEADER?
    @GetMapping
    public List<CreditOrderResponseDto> getOrdersByClientId(@RequestHeader UUID clientId) {
        final List<CreditOrderEntity> creditOrders = creditOrderService.getCreditOrdersByClientId(clientId);
        return creditOrderMapper.toDtoList(creditOrders);
    }

    @PostMapping
    public void create(@RequestHeader UUID clientId, @RequestBody CreditOrderRequestDto creditOrderRequestDto) {
        final CreditOrderEntity orderEntity = creditOrderMapper.toEntity(creditOrderRequestDto);
        creditOrderService.create(clientId, orderEntity);
    }

    @PatchMapping
    public void approved(@RequestHeader UUID clientId, @RequestBody CreditOrderApprovedDto creditOrder) {
        final CreditOrderEntity orderEntity = creditOrderMapper.toStatus(creditOrder);
        creditOrderService.approved(clientId, orderEntity);
    }
}
