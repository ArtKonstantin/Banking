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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.aston.credit.entity.OrderStatusEnum.APPROVED_BY_CLIENT;
import static org.aston.credit.entity.OrderStatusEnum.INDIVIDUAL_CONDITIONS;
import static org.aston.credit.entity.OrderStatusEnum.PENDING;
import static org.aston.credit.entity.OrderStatusEnum.REJECT_BY_CLIENT;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreditOrderServiceTest {
    @InjectMocks
    private CreditOrderService creditOrder;
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

        assertThat(creditOrder.getCreditOrdersByClientId(clientId)).isEqualTo(creditOrders);
    }

    @Test
    void throwExceptionIfCreditOrdersIsEmpty() {
        assertThrows(EntityNotFoundException.class,
                () -> creditOrder.getCreditOrdersByClientId(null));
    }

    @Test
    void create() {
        CreditOrderEntity order = CreditOrderHelper.getCreditOrder();
        CreditProductEntity creditProduct = mock(CreditProductEntity.class);

        when(creditProductRepository.getReferenceById(order.getCreditProduct().getId())).thenReturn(creditProduct);
        when(creditProduct.isProductIsActive()).thenReturn(true);
        when(creditProduct.getMinSum()).thenReturn(BigDecimal.valueOf(10000));
        when(creditProduct.getMaxSum()).thenReturn(BigDecimal.valueOf(100000));
        when(creditProduct.getMinPeriodMonths()).thenReturn(3);
        when(creditProduct.getMaxPeriodMonths()).thenReturn(24);

        creditOrder.create(clientId, order);
        verify(creditOrderRepository, times(1)).save(order);
    }

    @Test
    void throwExceptionIfProductBadRequest_productIsNotActive() {
        CreditOrderEntity order = CreditOrderHelper.getCreditCreate();
        CreditProductEntity creditProduct = mock(CreditProductEntity.class);

        when(creditProductRepository.getReferenceById(order.getCreditProduct().getId())).thenReturn(creditProduct);
        when(creditProduct.isProductIsActive()).thenReturn(false);

        assertThrows(BadRequestException.class,
                () -> creditOrder.create(clientId, order));
    }

    @Test
    void throwExceptionIfProductBadRequest_amountLessMinSum() {
        CreditOrderEntity order = CreditOrderHelper.getCreditCreate();
        CreditProductEntity creditProduct = mock(CreditProductEntity.class);
        order.setAmount(BigDecimal.valueOf(150000));

        when(creditProductRepository.getReferenceById(order.getCreditProduct().getId())).thenReturn(creditProduct);
        when(creditProduct.isProductIsActive()).thenReturn(true);
        when(creditProduct.getMinSum()).thenReturn(BigDecimal.valueOf(151000));

        assertThrows(BadRequestException.class,
                () -> creditOrder.create(clientId, order));
    }

    @Test
    void throwExceptionIfProductBadRequest_periodMoreMaxPeriodMonths() {
        CreditOrderEntity order = CreditOrderHelper.getCreditCreate();
        CreditProductEntity creditProduct = mock(CreditProductEntity.class);
        order.setAmount(BigDecimal.valueOf(120000));
        order.setPeriodMonths(36);

        when(creditProductRepository.getReferenceById(order.getCreditProduct().getId())).thenReturn(creditProduct);
        when(creditProduct.isProductIsActive()).thenReturn(true);
        when(creditProduct.getMinSum()).thenReturn(BigDecimal.valueOf(100000));
        when(creditProduct.getMaxSum()).thenReturn(BigDecimal.valueOf(149000));
        when(creditProduct.getMinPeriodMonths()).thenReturn(3);
        when(creditProduct.getMaxPeriodMonths()).thenReturn(24);

        assertThrows(BadRequestException.class,
                () -> creditOrder.create(clientId, order));
    }

    @Test
    void approved_ifApprovedByClient() {
        CreditOrderEntity order = CreditOrderHelper.getCreditOrder();
        CreditOrderEntity orderRequest = mock(CreditOrderEntity.class);

        when(orderRequest.getId()).thenReturn(1L);
        when(creditOrderRepository.getReferenceById(orderRequest.getId())).thenReturn(order);
        when(orderRequest.getStatus()).thenReturn(APPROVED_BY_CLIENT);

        creditOrder.approved(clientId, orderRequest);
        verify(creditOrderRepository, times(1)).save(order);
    }

    @Test
    void throwException_ifApprovedByClient_andStatusNotApprovedByBank() {
        CreditOrderEntity order = CreditOrderHelper.getCreditOrder();
        CreditOrderEntity orderRequest = mock(CreditOrderEntity.class);

        order.setStatus(PENDING);
        when(orderRequest.getId()).thenReturn(1L);
        when(creditOrderRepository.getReferenceById(orderRequest.getId())).thenReturn(order);
        when(orderRequest.getStatus()).thenReturn(APPROVED_BY_CLIENT);

        assertThrows(BadRequestException.class,
                () -> creditOrder.approved(clientId, orderRequest));
    }

    @Test
    void approved_ifRejectByClient() {
        CreditOrderEntity order = CreditOrderHelper.getCreditOrder();
        CreditOrderEntity orderRequest = mock(CreditOrderEntity.class);

        when(orderRequest.getId()).thenReturn(1L);
        when(creditOrderRepository.getReferenceById(orderRequest.getId())).thenReturn(order);
        when(orderRequest.getStatus()).thenReturn(REJECT_BY_CLIENT);

        creditOrder.approved(clientId, orderRequest);
        verify(creditOrderRepository, times(1)).save(order);
    }

    @Test
    void throwException_ifRejectByClient_andStatusNotApprovedByBankAndNotPending() {
        CreditOrderEntity order = CreditOrderHelper.getCreditOrder();
        CreditOrderEntity orderRequest = mock(CreditOrderEntity.class);

        order.setStatus(INDIVIDUAL_CONDITIONS);
        when(orderRequest.getId()).thenReturn(1L);
        when(creditOrderRepository.getReferenceById(orderRequest.getId())).thenReturn(order);
        when(orderRequest.getStatus()).thenReturn(REJECT_BY_CLIENT);

        assertThrows(BadRequestException.class,
                () -> creditOrder.approved(clientId, orderRequest));
    }


    @Test
    void throwException_ifClientIdNotEquals() {
        CreditOrderEntity order = CreditOrderHelper.getCreditOrder();
        CreditOrderEntity orderRequest = CreditOrderHelper.getCreditOrderApproved();

        when(creditOrderRepository.getReferenceById(orderRequest.getId())).thenReturn(order);

        assertThrows(ForbiddenException.class,
                () -> creditOrder.approved(UUID.fromString("0799f8b8-0000-4818-b1ba-5e64f88f6d03"), orderRequest));
    }
}
