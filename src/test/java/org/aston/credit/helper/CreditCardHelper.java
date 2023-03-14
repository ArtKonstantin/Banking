package org.aston.credit.helper;

import org.aston.credit.dto.CreditCardRequestDto;
import org.aston.credit.entity.CardStatusEnum;
import org.aston.credit.entity.CreditCardEntity;

public class CreditCardHelper {

    public static CreditCardEntity getCreditCard() {
        CreditCardEntity creditCard = new CreditCardEntity();
        creditCard.setCardNumber("1234567891234567");
        creditCard.setCardStatus(CardStatusEnum.BLOCKED);
        creditCard.setTransactionLimit(10000);
        return creditCard;
    }

    public static CreditCardRequestDto getCreditCardDto() {

        return CreditCardRequestDto.builder().
                cardNumber("1234567891234567").
                cardStatus(CardStatusEnum.BLOCKED).
                newPin("1234").
                transactionLimit("10000").
                build();
    }
}
