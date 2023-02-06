package org.aston.credit.service;

import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.repository.CreditAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditAccountService {
    private final CreditAccountRepository creditAccountRepository;

    @Autowired
    public CreditAccountService(CreditAccountRepository creditAccountRepository) {
        this.creditAccountRepository = creditAccountRepository;
    }

    public List<CreditAccountEntity> getAll() {
        return creditAccountRepository.findAll();
    }
}
