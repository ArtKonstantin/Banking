package org.aston.credit.controller;

import jakarta.persistence.EntityNotFoundException;
import org.aston.credit.dto.responses.CreditInformationResponseDto;
import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.entity.CreditAgreementEntity;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.entity.PaymentScheduleEntity;
import org.aston.credit.entity.enums.CreditStatusEnum;
import org.aston.credit.entity.enums.CreditTypeEnum;
import org.aston.credit.entity.enums.OrderStatusEnum;
import org.aston.credit.helper.CreditHelper;
import org.aston.credit.mapper.CreditMapper;
import org.aston.credit.mapper.CreditOrderMapper;
import org.aston.credit.service.CreditOrderService;
import org.aston.credit.service.CreditService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CreditController.class)
class CreditControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreditService creditService;

    @MockBean
    private CreditOrderService creditOrderService;

    @MockBean
    private CreditMapper creditMapper;

    @MockBean
    private CreditOrderMapper creditOrderMapper;

    @Test
    void whenGetSchedule_thenReturnOk() throws Exception {
        CreditEntity expected = CreditHelper.getCredit();

        Mockito.when(creditService.schedule(Mockito.any(), Mockito.anyInt())).thenReturn(expected);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/credit/credits/1/schedule")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("clientId", "0799f8b8-729d-4818-b1ba-5e64f88f6d03"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void whenGetAgreement_thenReturnOk() throws Exception {
        CreditEntity creditExpected = CreditHelper.getCredit();
        PaymentScheduleEntity paymentExpected = CreditHelper.getPayments().get(0);
        CreditAccountEntity accountExpected = CreditHelper.getCreditAccount();

        Mockito.when(creditService.credit(1)).thenReturn(creditExpected);
        Mockito.when(creditService.payment(creditExpected.getCreditAccount().getPaymentSchedule())).thenReturn(paymentExpected);
        Mockito.when(creditService.debt(creditExpected.getCreditAccount())).thenReturn(accountExpected);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/credit/credits/1/details")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("creditId", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void whenGetShortInformation_thenReturnOk() throws Exception {
        List<CreditOrderEntity> expected = new ArrayList<>();
        expected.add(new CreditOrderEntity(1L, UUID.randomUUID(), new CreditEntity(), new CreditProductEntity(),
                OrderStatusEnum.APPROVED_BY_BANK, BigDecimal.valueOf(90000.00), 6, LocalDate.now(),
                BigDecimal.valueOf(100000.00), BigDecimal.valueOf(50000.00),
                "123456789012"));
        expected.add(new CreditOrderEntity(2L, UUID.randomUUID(), new CreditEntity(), new CreditProductEntity(),
                OrderStatusEnum.APPROVED_BY_BANK, BigDecimal.valueOf(90000.00), 6, LocalDate.now().minusDays(15),
                BigDecimal.valueOf(100000.00), BigDecimal.valueOf(50000.00),
                "123456789012"));
        Mockito.when(creditOrderService.getCreditOrdersByClientId(Mockito.any()))
                .thenReturn(expected);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/credit/credits/information/short")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("clientId", "0799f8b8-729d-4818-b1ba-5e64f88f6d03"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void whenGetInformation_thenReturnOk() throws Exception {
        CreditEntity expected = new CreditEntity(1L, new CreditAgreementEntity(), new CreditOrderEntity(),
                CreditTypeEnum.CONSUMER_CREDIT, BigDecimal.valueOf(900000.00), "RUB",
                BigDecimal.valueOf(0.15), true, 0, CreditStatusEnum.ACTIVE,
                new CreditAccountEntity());
        CreditInformationResponseDto expectedDto = new CreditInformationResponseDto(1L, "someCredit", "RUB",
                "1000000001", BigDecimal.valueOf(900000.00), BigDecimal.valueOf(0.15), "10.02.2025",
                LocalDate.now());
        Mockito.when(creditService.getInformation(Mockito.any()))
                .thenReturn(expected);
        Mockito.when(creditMapper.toInformationDto(Mockito.any())).thenReturn(expectedDto);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/credit/credits/information/detailed")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("clientId", "0799f8b8-729d-4818-b1ba-5e64f88f6d03")
                        .param("creditId", "5"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void whenGetShortInformation_thenThrowNotFoundException() throws Exception {
        Mockito.when(creditService.getInformation(Mockito.any()))
                .thenThrow(NoSuchElementException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/credit/credits/information")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("clientId", "0799f8b8-729d-4818-b1ba-5e64f88f6d03")
                        .param("creditId", "5"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void whenGetShortInformation_thenThrowInternalServerException() throws Exception {
        Mockito.when(creditService.getInformation(Mockito.any()))
                .thenThrow(InternalError.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/credit/credits/information/detailed")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("clientId", "0799f8b8-729d-4818-b1ba-5e64f88f6d03")
                        .param("creditId", "5"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void whenGetInformation_thenThrowInternalServerException() throws Exception {
        Mockito.when(creditOrderService.getCreditOrdersByClientId(Mockito.any()))
                .thenThrow(InternalError.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/credit/credits/information/short")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("clientId", "0799f8b8-729d-4818-b1ba-5e64f88f6d03"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void whenGetInformation_thenThrowNotFoundException() throws Exception {
        Mockito.when(creditOrderService.getCreditOrdersByClientId(Mockito.any()))
                .thenThrow(EntityNotFoundException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/credit/credits/information/short")
                        .accept(MediaType.APPLICATION_JSON)
                        .param("clientId", "0799f8b8-729d-4818-b1ba-5e64f88f6d03"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}