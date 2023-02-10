package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditProductResponceDTO;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.repository.CreditProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditProductService {
    private final CreditProductRepository creditProductRepository;

    private final Function<CreditProductEntity, CreditProductResponceDTO> creditProductEntityToCreditProductResponceDTO = entity -> new CreditProductResponceDTO(
            entity.getId(),
            entity.getCreditOrders(),
            entity.getProductName(),
            entity.getMinSum(),
            entity.getMaxSum(),
            entity.getCurrencyCode(),
            entity.getMinInterestRate(),
            entity.getMaxInterestRate(),
            entity.isNeedGuarantees(),
            entity.isDeliveryInCash(),
            entity.isEarlyRepayment(),
            entity.getMinPeriodMonths(),
            entity.getMaxPeriodMonths(),
            entity.isProductIsActive(),
            entity.getProductDetails(),
            entity.getCalculationMode(),
            entity.getGracePeriodMonths(),
            entity.isNeedIncomeDetails()
    );

    public List<CreditProductResponceDTO> getAll() {

        return creditProductRepository.findAll().stream().map(creditProductEntityToCreditProductResponceDTO).collect(Collectors.toList());
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
