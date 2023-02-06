package org.aston.credit.repository;

import org.aston.credit.entity.CreditAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditAccountRepository extends JpaRepository<CreditAccountEntity, Long> {
}
