package org.aston.credit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
        description = "Отвечает за эндпоинты CR.1 - CR.3; CC.6")
@Validated
@RequestMapping("/api/v1/credits")
public class CreditController {
    private final CreditService creditService;
    private final CreditMapper creditMapper;
    private final CreditOrderService creditOrderService;
    private final CreditOrderMapper creditOrderMapper;

    @GetMapping("/{creditId}/schedule")
    @Operation(summary = "CR.3 - Отправка графика платежей по кредиту.",
            description = "Просматривая конкретный кредит пользователь может запросить график платежей")
    public ScheduleResponseDto schedule(
            @RequestHeader(name = "clientId") @Parameter(description = Constants.UUID, required = true)
            @NotBlank(message = Constants.UUID_BLANK)
            @Pattern(regexp = Constants.UUID_PATTERN, message = Constants.UUID_INVALID) UUID clientId,
            @PathVariable(name = "creditId") @Parameter(description = Constants.CREDIT_ID, required = true)
            @NotBlank(message = Constants.CREDIT_ID_BLANK) Long creditId) {
        CreditEntity credit = creditService.schedule(clientId, creditId);
        return creditMapper.toDto(credit);
    }

    @GetMapping("/{agreementId}/details")
    @Operation(summary = "CC.6 - Получение реквизитов кредита для платежа.",
            description = "Возвращает номер кредитного договора и сумму для погашения кредита")
    public AgreementResponceDto agreement(
            @PathVariable(name = "agreementId") @Parameter(description = Constants.AGREEMENT_ID, required = true)
            @NotBlank(message = Constants.AGREEMENT_ID_BLANK)
            @Pattern(regexp = "\\d{20}", message = Constants.AGREEMENT_ID_INVALID)
            Long agreementId) {
        CreditEntity credit = creditService.credit(agreementId);
        PaymentScheduleEntity payment = creditService.payment(credit.getCreditAccount().getPaymentSchedule());
        CreditAccountEntity debt = creditService.debt(credit.getCreditAccount());
        return creditMapper.agreementToDto(credit, payment, debt);
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
    @Operation(summary = "CR.1 - Отправка краткой информации о кредитных продуктах клиента.",
            description = "Возвращает список с краткой информацией о всех кредитных продуктов клиента")
    public List<ShortCreditResponseDto> getShortInformation(
            @RequestParam(name = "clientId") @Parameter(description = Constants.UUID, required = true)
            @NotBlank(message = Constants.UUID_BLANK)
            @Pattern(regexp = Constants.UUID_PATTERN, message = Constants.UUID_INVALID)
            UUID clientId) {
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
    @Operation(summary = "CR.2 - Отправка полной информации о кредитном продукте клиента.",
            description = "Возвращает список всех кредитных продуктов клиента")
    public ResponseEntity<CreditInformationResponseDto> getInformation(
            @RequestParam(name = "creditId") @Parameter(description = Constants.CREDIT_ID, required = true)
            @NotBlank(message = Constants.CREDIT_ID_BLANK)
            Long creditId,
            @RequestParam(name = "clientId") @Parameter(description = Constants.UUID, required = true)
            @NotBlank(message = Constants.UUID_BLANK)
            @Pattern(regexp = Constants.UUID_PATTERN, message = Constants.UUID_INVALID)
            UUID clientId) {
        return ResponseEntity.ok(creditMapper
                .toInformationDto(creditService.getInformation(creditId)));
    }
}
