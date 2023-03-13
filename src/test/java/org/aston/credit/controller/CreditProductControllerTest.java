package org.aston.credit.controller;

import org.aston.credit.entity.CalculationModeEnum;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.mapper.CreditProductMapper;
import org.aston.credit.service.CreditProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CreditProductController.class)
class CreditProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditProductService creditProductService;

    @MockBean
    private CreditProductMapper creditProductMapper;

    private List<CreditProductEntity> expected = new ArrayList<>();

    @BeforeEach
    void setUp() {
        expected.add(new CreditProductEntity(1, List.of(new CreditOrderEntity()), "Стартовый",
                BigDecimal.valueOf(100000.00), BigDecimal.valueOf(1000000.00), "RUB",
                BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.30), false, false,
                true, 6, 36, true,
                "Стандартный кредит", CalculationModeEnum.ANNUITY, 0, true));
        expected.add(new CreditProductEntity(2, List.of(new CreditOrderEntity()), "Ne Стартовый",
                BigDecimal.valueOf(10000.00), BigDecimal.valueOf(100000.00), "RUB",
                BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.30), false, false,
                true, 6, 36, true,
                "Стандартный кредит", CalculationModeEnum.ANNUITY, 0, true));
        expected.add(new CreditProductEntity(3, List.of(new CreditOrderEntity()), "Ne Ne Стартовый",
                BigDecimal.valueOf(1000.00), BigDecimal.valueOf(10000.00), "RUB",
                BigDecimal.valueOf(0.05), BigDecimal.valueOf(0.30), false, false,
                true, 6, 36, true,
                "Стандартный кредит", CalculationModeEnum.ANNUITY, 0, true));
    }

    @Test
    void getAllActive() throws Exception {
        Mockito.when(creditProductService.getAllActive())
                .thenReturn(expected);
        mockMvc.perform(MockMvcRequestBuilders.get("/auth/credit-products")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}