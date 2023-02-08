package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.PaymentScheduleEntity;
import org.aston.credit.repository.PaymentScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentScheduleService {
    private final PaymentScheduleRepository paymentScheduleRepository;

    public List<PaymentScheduleEntity> getAll() {
        return paymentScheduleRepository.findAll();
    }
}
