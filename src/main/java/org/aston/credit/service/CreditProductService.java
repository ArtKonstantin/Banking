package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditProductResponseDto;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.mapper.CreditProductMapper;
import org.aston.credit.repository.CreditProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditProductService {
    private final CreditProductRepository creditProductRepository;
    private final CreditProductMapper creditProductMapper;

    public List<CreditProductResponseDto> getAll() {
        List<CreditProductEntity> creditProducts = creditProductRepository.findAll();
        return creditProductMapper.creditProductsToCreditProductDto(creditProducts);
    }

    public CreditProductResponseDto getById(long id) {
        CreditProductEntity creditProduct = creditProductRepository.getReferenceById(id);
        return creditProductMapper.creditProductToCreditProductDto(creditProduct);
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
