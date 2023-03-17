package org.aston.credit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
        description = "Отвечает за эндпоинты 01 - 04")
@Validated
@RequestMapping("/api/v1/loan-applications")
public class CreditOrderController {
    private final CreditOrderService creditOrderService;
    private final CreditOrderMapper creditOrderMapper;

    // TODO-0: UUID клиента передается в HEADER?
    @GetMapping
    @Operation(summary = "02 - Получение данных о кредитных заявках.",
            description = "Приложение посылает запрос серверу, чтобы отобразить пользователю его кредитные заявки и информацию о них")
    public List<CreditOrderResponseDto> getOrdersByClientId(
            @RequestHeader(name = "clientId") @Parameter(description = Constants.UUID, required = true)
            @NotBlank(message = Constants.UUID_BLANK)
            @Pattern(regexp = Constants.UUID_PATTERN, message = Constants.UUID_INVALID) UUID clientId) {
        final List<CreditOrderEntity> creditOrders = creditOrderService.getCreditOrdersByClientId(clientId);
        return creditOrderMapper.toDtoList(creditOrders);
    }

    @PostMapping
    @Operation(summary = "01 - Создание заявки на кредит.",
            description = "Авторизованный пользователь приложения заполняет форму \"Заявка на кредит\" и нажимает " +
                    "кнопку \"Отправить заявку\"")
    public void create(@RequestHeader(name = "clientId") @Parameter(description = Constants.UUID, required = true)
                       @NotBlank(message = Constants.UUID_BLANK)
                       @Pattern(regexp = Constants.UUID_PATTERN, message = Constants.UUID_INVALID) UUID clientId,
                       @Valid @RequestBody CreditOrderRequestDto creditOrderRequestDto) {
        final CreditOrderEntity orderEntity = creditOrderMapper.toEntity(creditOrderRequestDto);
        creditOrderService.create(clientId, orderEntity);
    }

    @PatchMapping
    @Operation(summary = "03/04 - Подтверждение/Отказ от кредитной заявки.",
            description = "После вызова эндпоинта происходит изменения статуса кредитной заявки на " +
                    "\"APPROVED BY CLIENT\" или же на \"REJECTED BY CLIENT\"")
    public void approved(@RequestHeader(name = "clientId") @Parameter(description = Constants.UUID, required = true)
                         @NotBlank(message = Constants.UUID_BLANK)
                         @Pattern(regexp = Constants.UUID_PATTERN, message = Constants.UUID_INVALID) UUID clientId,
                         @Valid @RequestBody CreditOrderApprovedRequestDto creditOrder) {
        final CreditOrderEntity orderEntity = creditOrderMapper.toStatus(creditOrder);
        creditOrderService.approved(clientId, orderEntity);
    }
}
