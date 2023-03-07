package org.aston.credit.service;

import lombok.RequiredArgsConstructor;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.repository.CreditRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditRepository creditRepository;

    public List<CreditEntity> getAll() {
        return creditRepository.findAll();
    }

    /**
     * CR.2 - Отправка полной информации о кредитном продукте клиента.
     * <p>
     * Запрос на получение подробной информации о действующем кредитном продукте пользователя из БД.
     * <p>
     * Происходит SELECT запрос в БД на поиск информации о кредите по его id, если такой id есть -
     * возвращается вся информация из БД по кредиту для передачи её в контроллер и последующего маппинга в ДТО,
     * если её нет - выбрасывыается исключение
     * <p>
     * Следующие поля не включены в маппинг: number (номер кредитного договра), payment date, три неописанных поля.
     *
     * @param creditId id крединтного продукта
     * @return CreditEntity с информацией о кредитных продуктах клиента
     * @throws jakarta.persistence.EntityNotFoundException если клиента с таким id не существует
     */
    public CreditEntity getInformation(Long creditId) {
        return creditRepository.findById(creditId).orElseThrow();
    }
}
