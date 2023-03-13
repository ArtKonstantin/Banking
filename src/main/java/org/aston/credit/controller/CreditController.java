package org.aston.credit.controller;

import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.CreditInformationResponseDto;
import org.aston.credit.dto.ShortCreditResponseDto;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.mapper.CreditMapper;
import org.aston.credit.mapper.CreditOrderMapper;
import org.aston.credit.service.CreditOrderService;
import org.aston.credit.service.CreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credits")
public class CreditController {
    private final CreditService creditService;

    private final CreditMapper creditMapper;

    private final CreditOrderService creditOrderService;

    private final CreditOrderMapper creditOrderMapper;

    @GetMapping
    public List<CreditEntity> getAll() {
        return creditService.getAll();
    }

    /**
     * CR.1 - Отправка краткой информации о кредитных продуктах клиента.
     * <p>
     * Пользователь заполняет форму "Заявка на кредит" в приложении и нажимает кнопку "Отправить заявку".
     *
     * @param clientId uuid клинета
     * @return List of {@link ShortCreditResponseDto}
     * @throws jakarta.persistence.EntityNotFoundException если клиента с таким id не существует
     */
    @GetMapping("/information/short")
    public List<ShortCreditResponseDto> getShortInformation(@RequestParam(name = "clientId") UUID clientId) {
        return creditOrderMapper
                .toListShortDto(creditOrderService.getCreditOrdersByClientId(clientId));
    }

    /**
     * CR.2 - Отправка полной информации о кредитном продукте клиента.
     * <p>
     * Запрос на получение подробной информации о действующем кредитном продукте пользователя из БД.
     * <p>
     * <b>Следующие поля не включены в маппинг:</b> <i>number</i> (номер кредитного договра),
     * <i>payment date</i>, <i>три неописанных поля</i>.
     *
     * @param creditId id крединтного продукта
     * @param clientId uuid клинета (в документации не описано как он должен учавствовать в этом эндпоинте)
     * @return {@link CreditInformationResponseDto} с информацией о кредитных продуктах клиента
     * @throws jakarta.persistence.EntityNotFoundException если клиента с таким id не существует
     */
    @GetMapping("/information")
    public ResponseEntity<CreditInformationResponseDto> getInformation(@RequestParam(name = "creditId") Long creditId,
                                                                       @RequestParam(name = "clientId") UUID clientId) {
        return ResponseEntity.ok(creditMapper
                .toInformationDto(creditService.getInformation(creditId)));
    }
}
