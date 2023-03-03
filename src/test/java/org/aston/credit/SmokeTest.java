package org.aston.credit;

import org.aston.credit.controller.CreditCardController;
import org.aston.credit.controller.CreditController;
import org.aston.credit.controller.CreditOrderController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {
    @Autowired
    private CreditController creditController;

    @Autowired
    private CreditCardController creditCardController;

    @Autowired
    private CreditOrderController creditOrderController;

    @Test
    public void contextLoads() {
        assertThat(creditController).isNotNull();
        assertThat(creditCardController).isNotNull();
        assertThat(creditOrderController).isNotNull();
    }
}
