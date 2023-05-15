package org.aston.credit.repository;

import org.aston.credit.entity.CreditOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CreditOrderRepository extends JpaRepository<CreditOrderEntity, Long> {
    List<CreditOrderEntity> findAllByClientId(UUID clientId);
    CreditOrderEntity findByNumber(String number);
}
