package org.aston.credit.repository;

import org.aston.credit.entity.CreditEntity;
import org.aston.credit.entity.CreditOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<CreditEntity, Long> {
    CreditEntity findByCreditAgreementId(long id);

    CreditEntity findByCreditOrder(CreditOrderEntity creditOrder);
}
