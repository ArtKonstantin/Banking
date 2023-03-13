package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.CreditProductEntity;
import org.aston.credit.exception.BadRequestException;
import org.aston.credit.exception.ForbiddenException;
import org.aston.credit.helper.CreditOrderHelper;
import org.aston.credit.repository.CreditOrderRepository;
import org.aston.credit.repository.CreditProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    @Mock
    private CreditProductRepository creditProductRepository;
    private final UUID clientId = CreditOrderHelper.CLIENT_ID;

    @Test
    void getCreditOrdersByClientId() {
        List<CreditOrderEntity> creditOrders = new ArrayList<>();
        creditOrders.add(CreditOrderHelper.getCreditOrder());

        when(creditOrderRepository.findAllByClientId(clientId)).thenReturn(creditOrders);

        assertThat(creditOrderService.getCreditOrdersByClientId(clientId)).isEqualTo(creditOrders);
    }

    @Test
    void throwExceptionIfCreditOrdersIsEmpty() {
        assertThrows(EntityNotFoundException.class,
                () -> creditOrderService.getCreditOrdersByClientId(null));
    }

    @Test
    void create() {
        CreditOrderEntity order = CreditOrderHelper.getCreditCreate();

        orderService.create(clientId, order);

        verify(orderService, times(1)).create(clientId, order);
    }

    @Test
    void throwExceptionIfProductBadRequest() {
        CreditProductEntity product = CreditOrderHelper.getCreditProduct();
        CreditOrderEntity order = CreditOrderHelper.getCreditOrder();

        when(creditProductRepository.getReferenceById(order.getCreditProduct().getId())).thenReturn(product);

        assertThrows(BadRequestException.class,
                () -> creditOrderService.create(clientId, order));
    }

    @Test
    void approved() {
        CreditOrderEntity order = CreditOrderHelper.getCreditOrderApproved();

        orderService.approved(clientId, order);

        verify(orderService, times(1)).approved(clientId, order);
    }

    @Test
    void throwExceptionIfClientIdNotEquals() {
        CreditOrderEntity order = CreditOrderHelper.getCreditOrder();
        CreditOrderEntity orderRequest = CreditOrderHelper.getCreditOrderApproved();

        when(creditOrderRepository.getReferenceById(orderRequest.getId())).thenReturn(order);

        assertThrows(ForbiddenException.class,
                () -> creditOrderService.approved(UUID.fromString("0799f8b8-0000-4818-b1ba-5e64f88f6d03"), orderRequest));
    }
}
