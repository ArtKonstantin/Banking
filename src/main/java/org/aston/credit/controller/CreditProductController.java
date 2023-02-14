package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditProductResponceDto;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.service.CreditProductService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/credit-product")
public class CreditProductController {

    public final CreditProductService creditProductService;

    @GetMapping
    public List getAll() {
        return creditProductService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<CreditProductEntity> getById(@PathVariable long id) {
        return creditProductService.getById(id);
    }

    @PostMapping
    public void create(@RequestBody CreditProductEntity creditProduct) {
        creditProductService.create(creditProduct);
    }

    @PutMapping
    public void update(@RequestBody CreditProductEntity creditProduct) {
        creditProductService.update(creditProduct);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable long id) {
        creditProductService.removeById(id);
    }
}
