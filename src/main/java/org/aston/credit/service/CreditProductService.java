package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.mapper.CreditProductMapper;
import org.aston.credit.repository.CreditProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditProductService {
    private final CreditProductRepository creditProductRepository;
    private final CreditProductMapper creditProductMapper;

    public List getAll() {
        return creditProductRepository.findAll().stream().map(creditProductMapper::mapperToDto).collect(Collectors.toList());
    }

    public Optional<CreditProductEntity> getById(long id) {
        return creditProductRepository.findById(id);
    }

    public void create(CreditProductEntity creditProduct) {
        creditProductRepository.save(creditProduct);
    }

    public void update(CreditProductEntity creditProduct) {
        creditProductRepository.save(creditProduct);
    }

    public void removeById(long id) {
        creditProductRepository.deleteById(id);
    }
}
