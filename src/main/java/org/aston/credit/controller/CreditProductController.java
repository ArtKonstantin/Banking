package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditProductResponseDto;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.mapper.CreditProductMapper;
import org.aston.credit.service.CreditProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/credit-products")
public class CreditProductController {

    public final CreditProductService creditProductService;

    private final CreditProductMapper creditProductMapper;

    /**
     * A-PROD.1 - Отправка информации об активных кредитных продуктах
     *
     * @return Список активных кредитных продуктов банка
     */
    @GetMapping
    public ResponseEntity<List<CreditProductResponseDto>> getAllActive() {
        List<CreditProductEntity> products = creditProductService.getAllActive();
        return ResponseEntity.ok(creditProductMapper.toDtoList(products));
    }
}
