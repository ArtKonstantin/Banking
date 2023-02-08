package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.repository.CreditAccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditAccountService {
    private final CreditAccountRepository creditAccountRepository;

    public List<CreditAccountEntity> getAll() {
        return creditAccountRepository.findAll();
    }
}
