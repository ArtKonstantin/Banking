package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditOrderRejectDto;
import org.aston.credit.dto.CreditOrderRequestDto;
import org.aston.credit.dto.CreditOrderResponseDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.mapper.CreditOrderMapper;
import org.aston.credit.service.CreditOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/api/v1/loan-applications")
public class CreditOrderController {
    private final CreditOrderService creditOrderService;
    private final CreditOrderMapper creditOrderMapper;

    @GetMapping("/get-all")
    public List<CreditOrderResponseDto> getAll() {
        final List<CreditOrderEntity> creditOrders = creditOrderService.getAll();
        final List<CreditOrderResponseDto> creditOrdersDto = creditOrderMapper.toDtoList(creditOrders);
        return creditOrdersDto;
    }

    // TODO-0: UUID клиента передается в HEADER?
    // TODO-2: OC.2 Получение данных о кредитных заявках
    @GetMapping
    public List<CreditOrderResponseDto> getOrdersByClientId(@RequestHeader UUID clientId) {
        return creditOrderService.getCreditOrdersByClientId(clientId);
    }

    // TODO-1: OC.1 Создание заявки на кредит
    @PostMapping
    public void create(@RequestHeader UUID clientId, @RequestBody CreditOrderRequestDto creditOrderRequestDto){
        final CreditOrderEntity orderEntity = creditOrderMapper.toEntity(creditOrderRequestDto);
        creditOrderService.create(clientId, orderEntity);
    }

    // TODO-3: CS-1 Отзыв кредитной заявки, CS-2 Подтверждение кредитной заявки
    @PatchMapping
    public void approved(@RequestHeader UUID clientId, @RequestBody CreditOrderRejectDto creditOrder) {
        final CreditOrderEntity orderEntity = creditOrderMapper.toStatus(creditOrder);
        creditOrderService.approved(clientId, orderEntity);
    }
}
