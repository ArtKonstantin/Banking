package org.aston.credit.mapper;

import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.helper.CreditCardHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditCardMapperTest {

    final CreditCardMapper creditCardMapper = new CreditCardMapperImpl();

    @Test
    void newPinToEntity() {
        CreditCardEntity cardCard = creditCardMapper.newPinToEntity(CreditCardHelper.getCreditCardPinDto());
        final CreditCardEntity creditCardEntity = CreditCardHelper.getCreditCard();

        assertEquals(creditCardEntity.getCardNumber(), cardCard.getCardNumber());
    }

    @Test
    void newStatusToEntity() {
        CreditCardEntity cardCard = creditCardMapper.newStatusToEntity(CreditCardHelper.getCreditCardStatusDto());
        final CreditCardEntity creditCardEntity = CreditCardHelper.getCreditCard();

        assertEquals(creditCardEntity.getCardNumber(), cardCard.getCardNumber());
        assertEquals(creditCardEntity.getCardStatus(), cardCard.getCardStatus());
    }

    @Test
    void newLimitToEntity() {
        CreditCardEntity cardCard = creditCardMapper.newLimitToEntity(CreditCardHelper.getCreditCardLimitDto());
        final CreditCardEntity creditCardEntity = CreditCardHelper.getCreditCard();

        assertEquals(creditCardEntity.getCardNumber(), cardCard.getCardNumber());
    }
}
