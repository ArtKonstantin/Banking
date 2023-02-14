package org.aston.credit;

import static org.assertj.core.api.Assertions.assertThat;

import org.aston.credit.controller.CreditAccountController;
import org.aston.credit.controller.CreditAgreementController;
import org.aston.credit.controller.CreditCardController;
import org.aston.credit.controller.CreditController;
import org.aston.credit.controller.CreditOrderController;
import org.aston.credit.controller.CreditProductController;
import org.aston.credit.controller.PaymentScheduleController;
import org.aston.credit.controller.OperationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private CreditProductController creditProductController;

    @Autowired
    private CreditController creditController;

    @Autowired
    private CreditCardController creditCardController;

    @Autowired
    private CreditOrderController creditOrderController;

    @Autowired
    private OperationController operationController;

    @Autowired
    private PaymentScheduleController paymentScheduleController;

    @Autowired
    private CreditAgreementController creditAgreementController;

    @Autowired
    private CreditAccountController creditAccountController;

    @Test
    public void contextLoads(){
        assertThat(creditProductController).isNotNull();
        assertThat(creditController).isNotNull();
        assertThat(creditAccountController).isNotNull();
        assertThat(operationController).isNotNull();
        assertThat(paymentScheduleController).isNotNull();
        assertThat(creditCardController).isNotNull();
        assertThat(creditAgreementController).isNotNull();
        assertThat(creditOrderController).isNotNull();
    }
}
