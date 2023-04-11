package org.aston.credit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.aston.credit.Constants;
import org.aston.credit.dto.responses.AgreementResponceDto;
import org.aston.credit.dto.responses.CreditInformationResponseDto;
import org.aston.credit.dto.responses.ScheduleResponseDto;
import org.aston.credit.dto.responses.ShortCreditResponseDto;
import org.aston.credit.entity.CreditAccountEntity;
import org.aston.credit.entity.CreditEntity;
import org.aston.credit.entity.PaymentScheduleEntity;
import org.aston.credit.mapper.CreditMapper;
import org.aston.credit.mapper.CreditOrderMapper;
import org.aston.credit.service.CreditOrderService;
import org.aston.credit.service.CreditService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Контроллер кредитных продуктов клиента",
        description = "Отвечает за эндпоинты кредитов клиента")
@Validated
@RequestMapping("/api/v1/credit/credits")
public class CreditController {
    private final CreditService creditService;
    private final CreditMapper creditMapper;
    private final CreditOrderService creditOrderService;
    private final CreditOrderMapper creditOrderMapper;

    @GetMapping("/{creditId}/schedule")
    @Operation(summary = "07 - Маппинг Отправки графика платежей по кредиту",
            description = "Просматривая конкретный кредит пользователь может запросить график платежей")
    public ScheduleResponseDto schedule(
            @RequestHeader(name = "clientId")
            @Parameter(description = Constants.UUID, required = true) UUID clientId,
            @PathVariable(name = "creditId")
            @Parameter(description = Constants.CREDIT_ID, required = true) Long creditId) {
        CreditEntity credit = creditService.schedule(clientId, creditId);
        return creditMapper.toDto(credit);
    }

    @GetMapping("/{agreementId}/details")
    @Operation(summary = "08 - Маппинг Получения реквизитов кредита для платежа",
            description = "Возвращает номер кредитного договора и сумму для погашения кредита")
    public AgreementResponceDto agreement(
            @PathVariable(name = "agreementId")
            @Parameter(description = Constants.AGREEMENT_ID, required = true) Long agreementId) {
        CreditEntity credit = creditService.credit(agreementId);
        PaymentScheduleEntity payment = creditService.payment(credit.getCreditAccount().getPaymentSchedule());
        CreditAccountEntity debt = creditService.debt(credit.getCreditAccount());
        return creditMapper.agreementToDto(credit, payment, debt);
    }

    /**
     * 11 - Маппинг Отправки краткой информации о действующих кредитных продуктах пользователя.
     * <p>
     * Пользователь заполняет форму "Заявка на кредит" в приложении и нажимает кнопку "Отправить заявку".
     *
     * @param clientId uuid клинета
     * @return List of {@link ShortCreditResponseDto}
     * @throws jakarta.persistence.EntityNotFoundException если клиента с таким id не существует
     */
    @GetMapping("/information/short")
    @Operation(summary = "11 - Маппинг Отправки краткой информации о действующих кредитных продуктах пользователя",
            description = "Возвращает список с краткой информацией о всех кредитных продуктов клиента")
    public List<ShortCreditResponseDto> getShortInformation(
            @RequestParam(name = "clientId")
            @Parameter(description = Constants.UUID, required = true) UUID clientId) {
        return creditOrderMapper
                .toListShortDto(creditOrderService.getCreditOrdersByClientId(clientId));
    }

    /**
     * 12 - Маппинг Отправки подробной информации о действующем кредитном продукте пользователя.
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
    @GetMapping("/information/detailed")
    @Operation(summary = "12 - Маппинг Отправки подробной информации о действующем кредитном продукте пользователя",
            description = "Возвращает список всех кредитных продуктов клиента")
    public ResponseEntity<CreditInformationResponseDto> getInformation(
            @RequestParam(name = "creditId")
            @Parameter(description = Constants.CREDIT_ID, required = true) Long creditId,
            @RequestParam(name = "clientId")
            @Parameter(description = Constants.UUID, required = true) UUID clientId) {
        return ResponseEntity.ok(creditMapper
                .toInformationDto(creditService.getInformation(creditId)));
    }
}
