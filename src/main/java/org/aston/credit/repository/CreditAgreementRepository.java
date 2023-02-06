package org.aston.credit.repository;

import org.aston.credit.entity.CreditAgreementEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditAgreementRepository extends JpaRepository<CreditAgreementEntity, Long> {
}
