package org.aston.credit.helper;

import org.aston.credit.entity.CreditEntity;
import org.aston.credit.entity.CreditStatusEnum;
import org.aston.credit.entity.CreditTypeEnum;

import java.math.BigDecimal;
import java.util.UUID;

public class CreditHelper {
    public static final UUID CLIENT_ID = UUID.fromString("0799f8b8-729d-4818-b1ba-5e64f88f6d03");
    public static final long CREDIT_ID = 1;

    public static CreditEntity getCredit() {
        CreditEntity credit = new CreditEntity();
        credit.setId(1);
        credit.setCreditOrder(CreditOrderHelper.getCreditOrder());
        credit.setType(CreditTypeEnum.CONSUMER_CREDIT);
        credit.setCreditLimit(BigDecimal.valueOf(900000.00));
        credit.setCurrencyCode("RUB");
        credit.setInterestRate(BigDecimal.valueOf(0.15));
        credit.setPersonalGuarantees(true);
        credit.setGracePeriodMonths(0);
        credit.setCreditStatus(CreditStatusEnum.ACTIVE);
        return credit;
    }
}
