package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditOrderRejectDto;
import org.aston.credit.dto.CreditOrderRequestDto;
import org.aston.credit.dto.CreditOrderResponseDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.mapper.CreditOrderMapper;
import org.aston.credit.service.CreditOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    private final CreditOrderMapper creditOrderMapper;

    @GetMapping
    public List<CreditOrderResponseDto> getAll() {
        final List<CreditOrderEntity> creditOrders = creditOrderService.getAll();
        final List<CreditOrderResponseDto> creditOrdersDto = creditOrderMapper.toDtoList(creditOrders);
        return creditOrdersDto;
    }

    // TODO: UUID клиента передается в HEADER?
    @GetMapping("/get-list")
    public List<CreditOrderResponseDto> getOrdersByClientId(@RequestHeader UUID clientId) {
        return creditOrderService.getCreditOrdersByClientId(clientId);
    }

    // TODO: UUID клиента передается в HEADER?
    @PostMapping
    public void create(@RequestHeader UUID clientId, @RequestBody CreditOrderRequestDto creditOrderRequestDto){
        final CreditOrderEntity orderEntity = creditOrderMapper.toEntity(creditOrderRequestDto);
        creditOrderService.create(clientId, orderEntity);
    }

    // TODO: UUID клиента передается в HEADER?
    @PutMapping("/reject")
    public void reject(@RequestHeader UUID clientId, @RequestBody CreditOrderRejectDto creditOrder) {
        final CreditOrderEntity orderEntity = creditOrderMapper.toStatus(creditOrder);
        creditOrderService.reject(clientId, orderEntity);
    }

    // TODO: UUID клиента передается в HEADER?
    @PutMapping("/approved")
    public void approved(@RequestHeader UUID clientId, @RequestBody CreditOrderRejectDto creditOrder) {
        final CreditOrderEntity orderEntity = creditOrderMapper.toStatus(creditOrder);
        creditOrderService.approved(clientId, orderEntity);
    }
}
