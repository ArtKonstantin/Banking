package org.aston.credit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.responses.CreditProductByIdResponseDto;
import org.aston.credit.dto.responses.CreditProductResponseDto;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.mapper.CreditProductMapper;
import org.aston.credit.service.CreditProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Контроллер банковских кредитных продуктов")
@RequestMapping("/api/v1/credit/credit-products")
public class CreditProductController {
    public final CreditProductService creditProductService;
    private final CreditProductMapper creditProductMapper;

    @GetMapping
    @Operation(summary = "01- Маппинг Отправки активных кредитных продуктов",
            description = "Получение информации о действующих продуктах Банка")
    public List<CreditProductResponseDto> getAllActive() {
        List<CreditProductEntity> products = creditProductService.getAllActive();
        return creditProductMapper.toDtoList(products);
    }

    @GetMapping("/{creditProductId}")
    @Operation(summary = "02 - Просмотр активного кредитного продукта",
            description = "Получение подробной информации о кредитном продукте Банка")
    public CreditProductByIdResponseDto getById(@PathVariable final long creditProductId) {
        CreditProductEntity product = creditProductService.getById(creditProductId);
        return creditProductMapper.toDtoById(product);
    }
}
