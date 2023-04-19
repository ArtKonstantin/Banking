package org.aston.credit.repository;

import org.aston.credit.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditCardRepository extends JpaRepository<CreditCardEntity, UUID> {
    CreditCardEntity findByCardNumber(String cardNumber);
}
