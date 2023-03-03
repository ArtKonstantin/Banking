package org.aston.credit.service;

import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.helper.CreditOrderHelper;
import org.aston.credit.repository.CreditOrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditOrderServiceTest {
    @InjectMocks
    private CreditOrderService creditOrderService;

    @Mock
    private CreditOrderService orderService;

    @Mock
    private CreditOrderRepository creditOrderRepository;

    @Test
    void getCreditOrdersByClientId() {
        UUID clientId = CreditOrderHelper.CLIENT_ID;
        List<CreditOrderEntity> creditOrders = new ArrayList<>();
        creditOrders.add(CreditOrderHelper.getCreditOrder());

        when(creditOrderRepository.findAllByClientId(clientId)).thenReturn(creditOrders);

        assertThat(creditOrderService.getCreditOrdersByClientId(clientId)).isEqualTo(creditOrders);
    }

    @Test
    void approved() {
        UUID clientId = CreditOrderHelper.CLIENT_ID;
        CreditOrderEntity order = CreditOrderHelper.getCreditOrderApproved();

        orderService.approved(clientId, order);

        verify(orderService, times(1)).approved(clientId, order);
    }

    @Test
    void create() {
        UUID clientId = CreditOrderHelper.CLIENT_ID;
        CreditOrderEntity order = CreditOrderHelper.getCreditCreate();

        orderService.create(clientId, order);

        verify(orderService, times(1)).create(clientId, order);
    }
}