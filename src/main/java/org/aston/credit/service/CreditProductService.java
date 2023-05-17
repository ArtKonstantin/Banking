package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.exception.CreditServiceNotFoundException;
import org.aston.credit.repository.CreditProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.aston.credit.exception.EnumCodeAndCommentException.CREDIT_PRODUCT_NOT_ACTIVE;
import static org.aston.credit.exception.EnumCodeAndCommentException.CREDIT_PRODUCT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CreditProductService {
    private final CreditProductRepository creditProductRepository;

    public List<CreditProductEntity> getAllActive() {
        return creditProductRepository.findAllByProductIsActiveIsTrue();
    }

    public CreditProductEntity getById(long id) {
        final Optional<CreditProductEntity> creditProductOptional = creditProductRepository.findById(id);

        if (creditProductOptional.isEmpty()) {
            throw new CreditServiceNotFoundException(CREDIT_PRODUCT_NOT_FOUND);
        }

        CreditProductEntity creditProduct = creditProductOptional.get();

        if (!creditProduct.isProductIsActive()) {
            throw new CreditServiceNotFoundException(CREDIT_PRODUCT_NOT_ACTIVE);
        }

        return creditProduct;
    }
}
