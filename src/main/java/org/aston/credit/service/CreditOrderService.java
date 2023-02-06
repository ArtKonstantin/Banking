package org.aston.credit.service;

import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.repository.CreditOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditOrderService {
    private final CreditOrderRepository creditOrderRepository;

    @Autowired
    public CreditOrderService(CreditOrderRepository creditOrderRepository) {
        this.creditOrderRepository = creditOrderRepository;
    }

    public List<CreditOrderEntity> getAll() {
        return creditOrderRepository.findAll();
    }
}
