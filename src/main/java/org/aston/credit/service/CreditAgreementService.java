package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditAgreementEntity;
import org.aston.credit.repository.CreditAgreementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAgreementService {
    private final CreditAgreementRepository creditAgreementRepository;

    public List<CreditAgreementEntity> getAll() {
        return creditAgreementRepository.findAll();
    }
}
