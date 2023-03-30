package org.aston.credit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.responses.CreditProductResponseDto;
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
@Tag(name = "Контроллер банковских кредитных продуктов",
        description = "Отвечает за эндпоинты таблицы кредитных продуктов банка")
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
    @Operation(summary = "A-PROD.1 - Отправка информации об активных бакнковских кредитных продуктах.",
            description = "Возвращает список всех активных кредитных продуктов банка")
    public ResponseEntity<List<CreditProductResponseDto>> getAllActive() {
        List<CreditProductEntity> products = creditProductService.getAllActive();
        return ResponseEntity.ok(creditProductMapper.toDtoList(products));
    }
}
