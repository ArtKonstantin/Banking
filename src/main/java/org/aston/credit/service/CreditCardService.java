package org.aston.credit.service;

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
        final CreditCardEntity creditCard = creditCardRepository.findByCardNumber(creditCardEntity.getCardNumber());

        if (creditCard.getCardStatus().equals(creditCardEntity.getCardStatus())) {
            throw new BadRequestException();
        }

        creditCard.setCardStatus(creditCardEntity.getCardStatus());
        creditCardRepository.save(creditCard);
    }
}
