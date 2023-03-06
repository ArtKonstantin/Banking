package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.exception.BadRequestException;
import org.aston.credit.repository.CreditCardRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public void block(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = check(creditCardEntity);

        if (creditCard.getCardStatus().equals(creditCardEntity.getCardStatus())) {
            throw new BadRequestException();
        }

        creditCard.setCardStatus(creditCardEntity.getCardStatus());
        creditCardRepository.save(creditCard);
    }

    public void pin(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = check(creditCardEntity);

        creditCard.setPin(creditCardEntity.getPin());
        creditCardRepository.save(creditCard);
    }

    public void limit(CreditCardEntity creditCardEntity) {
        CreditCardEntity creditCard = check(creditCardEntity);

        creditCard.setTransactionLimit(creditCardEntity.getTransactionLimit());
        creditCardRepository.save(creditCard);
    }

    private CreditCardEntity check(CreditCardEntity creditCardEntity) {
        final CreditCardEntity creditCard = creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber());

        if (creditCard == null) {
            throw new EntityNotFoundException();
        }

        return creditCard;
    }
}
