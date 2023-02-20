package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditProductRequestDto;
import org.aston.credit.dto.CreditProductResponseDto;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.mapper.CreditProductMapper;
import org.aston.credit.service.CreditProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit-product")
public class CreditProductController {

    public final CreditProductService creditProductService;
    private final CreditProductMapper creditProductMapper;

    @GetMapping
    public List<CreditProductResponseDto> getAll() {
        final List<CreditProductEntity> creditProducts = creditProductService.getAll();
        final List<CreditProductResponseDto> creditProductsDto = creditProductMapper.toDtoList(creditProducts);
        return creditProductsDto;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id) {
        try {
            final CreditProductEntity creditProduct = creditProductService.getById(id);
            final CreditProductResponseDto creditProductDto = creditProductMapper.toDto(creditProduct);
            return new ResponseEntity<>(creditProductDto, HttpStatus.OK);
        } catch (Exception EntityNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public void create(@RequestBody CreditProductRequestDto creditProductRequestDto) {
        final CreditProductEntity creditProduct = creditProductMapper.toEntity(creditProductRequestDto);
        creditProductService.save(creditProduct);
    }

    @PutMapping
    public void update(@RequestBody CreditProductRequestDto creditProductRequestDto) {
        final CreditProductEntity creditProduct = creditProductMapper.toEntity(creditProductRequestDto);
        creditProductService.save(creditProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeById(@PathVariable long id) {
        try {
            creditProductService.removeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception EntityNotFoundException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
