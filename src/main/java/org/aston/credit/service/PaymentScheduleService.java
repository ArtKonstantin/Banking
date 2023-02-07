package org.aston.credit.service;

import org.aston.credit.entity.PaymentScheduleEntity;
import org.aston.credit.repository.PaymentScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentScheduleService {
    private final PaymentScheduleRepository paymentScheduleRepository;

    @Autowired
    public PaymentScheduleService(PaymentScheduleRepository paymentScheduleRepository) {
        this.paymentScheduleRepository = paymentScheduleRepository;
    }

    public List<PaymentScheduleEntity> getAll() {
        return paymentScheduleRepository.findAll();
    }
}
