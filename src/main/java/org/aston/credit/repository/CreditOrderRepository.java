package org.aston.credit.repository;

import org.aston.credit.entity.CreditOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditOrderRepository extends JpaRepository<CreditOrderEntity, Long> {
}
