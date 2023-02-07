package org.aston.credit.service;

import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;

    @Autowired
    public CreditCardService(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    public List<CreditCardEntity> getAll() {
       return creditCardRepository.findAll();
    }
}
