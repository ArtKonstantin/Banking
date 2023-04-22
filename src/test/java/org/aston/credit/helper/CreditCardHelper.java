package org.aston.credit.helper;

import org.aston.credit.dto.KafkaCreditCardDto;
import org.aston.credit.dto.KafkaPinCodeDto;
import org.aston.credit.dto.requests.ChangeCardLimitRequestDto;
import org.aston.credit.dto.requests.ChangeCardStatusRequestDto;
import org.aston.credit.dto.requests.ChangePinCardRequestDto;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.entity.enums.CardStatusEnum;

public class CreditCardHelper {

    public static CreditCardEntity getCreditCard() {
        CreditCardEntity creditCard = new CreditCardEntity();
        creditCard.setCardNumber("1234567891234567");
        creditCard.setCardStatus(CardStatusEnum.BLOCKED);
        creditCard.setTransactionLimit(10000);
        return creditCard;
    }

    public static KafkaPinCodeDto getKafkaPiCodeDto() {

        return KafkaPinCodeDto.builder().
                cardNumber("1234567891234567").
                pin("4321").
                build();
    }

    public static KafkaCreditCardDto getKafkaCreditCardDto() {
        return KafkaCreditCardDto.builder().
                cardNumber("1234567891234567").
                cardStatus(CardStatusEnum.BLOCKED).
                build();
    }

    public static ChangePinCardRequestDto getCreditCardPinDto() {

        return ChangePinCardRequestDto.builder().
                cardNumber("1234567891234567").
                newPin("1234").
                build();
    }

    public static ChangeCardLimitRequestDto getCreditCardLimitDto() {

        return ChangeCardLimitRequestDto.builder().
                cardNumber("1234567891234567").
                transactionLimit("10000").
                build();
    }

    public static ChangeCardStatusRequestDto getCreditCardStatusDto() {

        return ChangeCardStatusRequestDto.builder().
                cardNumber("1234567891234567").
                cardStatus(CardStatusEnum.BLOCKED).
                build();
    }
}
