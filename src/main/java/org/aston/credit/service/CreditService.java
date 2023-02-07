package org.aston.credit.service;

import org.aston.credit.entity.CreditEntity;
import org.aston.credit.repository.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditService {
    private final CreditRepository creditRepository;

    @Autowired
    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public List<CreditEntity> getAll() {
        return creditRepository.findAll();
    }
}
