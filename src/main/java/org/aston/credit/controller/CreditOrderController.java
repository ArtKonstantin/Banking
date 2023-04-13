package org.aston.credit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.aston.credit.Constants;
import org.aston.credit.dto.requests.CreditOrderApprovedRequestDto;
import org.aston.credit.dto.requests.CreditOrderRequestDto;
import org.aston.credit.dto.responses.CreditOrderResponseDto;
import org.aston.credit.entity.CreditOrderEntity;
import org.aston.credit.mapper.CreditOrderMapper;
import org.aston.credit.service.CreditOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Tag(name = "Контроллер заявок на кредиты",
        description = "Отвечает за эндпоинты кредитных заявок клиента")
@Validated
@RequestMapping("/api/v1/credit/loan-applications")
public class CreditOrderController {
    private final CreditOrderService creditOrderService;
    private final CreditOrderMapper creditOrderMapper;

    @PostMapping
    @Operation(summary = "03 - Маппинг Создания заявки на кредит",
            description = "Авторизованный пользователь приложения заполняет форму \"Заявка на кредит\" и нажимает " +
                    "кнопку \"Отправить заявку\"")
    public void create(
            @RequestHeader(name = "clientId")
            @Parameter(description = Constants.UUID, required = true) final UUID clientId,
            @RequestBody CreditOrderRequestDto creditOrderRequestDto)
    {
        final CreditOrderEntity orderEntity = creditOrderMapper.toEntity(creditOrderRequestDto);
        creditOrderService.create(clientId, orderEntity);
    }

    @GetMapping
    @Operation(summary = "04 - Маппинг Получения данных о кредитных заявках",
            description = "Приложение посылает запрос серверу, чтобы отобразить пользователю его кредитные заявки и информацию о них")
    public List<CreditOrderResponseDto> getOrdersByClientId(
            @RequestHeader(name = "clientId")
            @Parameter(description = Constants.UUID, required = true) final UUID clientId)
    {
        final List<CreditOrderEntity> creditOrders = creditOrderService.getCreditOrdersByClientId(clientId);
        return creditOrderMapper.toDtoList(creditOrders);
    }

    @PatchMapping
    @Operation(summary = "05/06 - Маппинг Отзыва/Подтверждения кредитной заявки",
            description = "После вызова эндпоинта происходит изменения статуса кредитной заявки на " +
                    "\"APPROVED BY CLIENT\" или же на \"REJECTED BY CLIENT\"")
    public void approved(
            @RequestHeader(name = "clientId")
            @Parameter(description = Constants.UUID, required = true) final UUID clientId,
            @RequestBody CreditOrderApprovedRequestDto creditOrder)
    {
        final CreditOrderEntity orderEntity = creditOrderMapper.toStatus(creditOrder);
        creditOrderService.approved(clientId, orderEntity);
    }
}
