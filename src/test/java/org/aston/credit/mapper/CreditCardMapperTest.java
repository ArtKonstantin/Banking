package org.aston.credit.mapper;

import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.helper.CreditCardHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardMapperTest {
    @Test
    void toEntity() {
        CreditCardEntity cardCard = new CreditCardMapperImpl().toEntity(CreditCardHelper.getCreditCardDto());
        final CreditCardEntity creditCardEntity = CreditCardHelper.getCreditCard();

        assertEquals(creditCardEntity.getCardNumber(), cardCard.getCardNumber());
        assertEquals(creditCardEntity.getCardStatus(), cardCard.getCardStatus());
    }
}
