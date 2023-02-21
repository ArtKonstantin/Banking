package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.repository.CreditProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditProductService {
    private final CreditProductRepository creditProductRepository;

    public List<CreditProductEntity> getAll() {
        return creditProductRepository.findAll();
    }

    public CreditProductEntity getById(long id) {
        return creditProductRepository.getReferenceById(id);
    }

    public void save(CreditProductEntity creditProduct) {
        creditProductRepository.save(creditProduct);
    }

    public void removeById(long id) {
        creditProductRepository.deleteById(id);
    }
}
