package org.aston.credit.helper;

import org.aston.credit.dto.CreditCardRequestDto;
import org.aston.credit.entity.CardStatusEnum;
import org.aston.credit.entity.CreditCardEntity;

public class CreditCardHelper {

    public static CreditCardEntity getCreditCard() {
        CreditCardEntity creditCard = new CreditCardEntity();
        creditCard.setCardNumber("1234567891234567");
        creditCard.setCardStatus(CardStatusEnum.BLOCKED);
        return creditCard;
    }

    public static CreditCardRequestDto getCreditCardDto() {
        return new CreditCardRequestDto(
                "1234567891234567",
                CardStatusEnum.BLOCKED
        );
    }
}
