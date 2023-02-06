package org.aston.credit.controller;

import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.service.CreditProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/credit-product")
public class CreditProductController {

    public final CreditProductService creditProductService;

    @Autowired
    public CreditProductController(CreditProductService creditProductService) {
        this.creditProductService = creditProductService;
    }

    @GetMapping
    public List<CreditProductEntity> getAll() {
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
