package org.aston.credit.service;

import org.aston.credit.entity.CreditAgreementEntity;
import org.aston.credit.repository.CreditAgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditAgreementService {
    private final CreditAgreementRepository creditAgreementRepository;

    @Autowired
    public CreditAgreementService(CreditAgreementRepository creditAgreementRepository) {
        this.creditAgreementRepository = creditAgreementRepository;
    }

    public List<CreditAgreementEntity> getAll() {
        return creditAgreementRepository.findAll();
    }
}
