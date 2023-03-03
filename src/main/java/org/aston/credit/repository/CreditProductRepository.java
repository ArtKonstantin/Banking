package org.aston.credit.repository;

import org.aston.credit.entity.CreditProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditProductRepository extends JpaRepository<CreditProductEntity, Long> {
}
