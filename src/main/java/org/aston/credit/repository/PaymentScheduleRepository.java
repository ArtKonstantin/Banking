package org.aston.credit.repository;

import org.aston.credit.entity.PaymentScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentScheduleRepository extends JpaRepository<PaymentScheduleEntity, Long> {
}
