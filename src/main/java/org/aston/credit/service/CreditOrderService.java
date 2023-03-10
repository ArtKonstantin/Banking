package org.aston.credit.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.entity.OrderStatusEnum;
import org.aston.credit.repository.CreditOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreditOrderService {
    private final CreditOrderRepository creditOrderRepository;

    public List<CreditOrderEntity> getAll() {
        return creditOrderRepository.findAll();
    }

    public void create(UUID clientId, CreditOrderEntity creditOrder) {
        creditOrder.setClientId(clientId);
        creditOrder.setStatus(OrderStatusEnum.PENDING);
        creditOrderRepository.save(creditOrder);
    }

    /**
     * CR.1 - Отправка краткой информации о кредитных продуктах клиента.
     * <p>
     * OC.2 - Получение данных о кредитных заявках.
     * <p>
     * Происходит SELECT запрос в БД на поиск информации о кредитных заявках клиента по его uuid, если такой id есть -
     * возвращается вся информация из БД по кредитным заявкам для передачи её в контроллер и последующего маппинга в ДТО,
     * если её нет - выбрасывыается исключение
     *
     * @param clientId uuid клинета
     * @return CreditOrderEntity с информацией о кредитных заявках клиента
     * @throws jakarta.persistence.EntityNotFoundException если клиента с таким id не существует
     */
    public List<CreditOrderEntity> getCreditOrdersByClientId(UUID clientId) {
        List<CreditOrderEntity> creditOrders = creditOrderRepository.findAllByClientId(clientId);

        if (creditOrders.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return creditOrders;
    }

}
