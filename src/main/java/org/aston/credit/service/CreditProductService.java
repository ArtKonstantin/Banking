package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.exception.CreditProductNotFoundException;
import org.aston.credit.repository.CreditProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditProductService {
    private final CreditProductRepository creditProductRepository;

    public List<CreditProductEntity> getAllActive() {
        return creditProductRepository.findAllByProductIsActiveIsTrue();
    }

    public CreditProductEntity getById(long id) {
        final CreditProductEntity creditProduct = creditProductRepository.getReferenceById(id);
        if (!creditProduct.isProductIsActive()) throw new CreditProductNotFoundException();
        return creditProduct;
    }
}
