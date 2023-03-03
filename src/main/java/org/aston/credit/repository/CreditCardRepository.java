package org.aston.credit.repository;

import org.aston.credit.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {
    CreditCardEntity findByCardNumber(String cardNumber);
}
