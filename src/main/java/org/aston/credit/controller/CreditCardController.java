package org.aston.credit.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aston.credit.dto.requests.ChangeCardLimitRequestDto;
import org.aston.credit.dto.requests.ChangeCardStatusRequestDto;
import org.aston.credit.dto.requests.ChangePinCardRequestDto;
import org.aston.credit.dto.responses.CreditCardResponseDto;
import org.aston.credit.entity.CreditCardEntity;
import org.aston.credit.mapper.CreditCardMapper;
import org.aston.credit.service.CreditCardService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Контроллер кредитных карт",
        description = "Отвечает за эндпоинты кредитных карт клиента")
@Validated
@RequestMapping("/api/v1/credit/credit-cards")
public class CreditCardController {
    private final CreditCardService creditCardService;
    private final CreditCardMapper creditCardMapper;

    @PatchMapping
    @Operation(summary = "14 -Маппинг Блокировки/разблокировки кредитной карты",
            description = "В данном endpoint необходимо осуществить блокировку кредитной карты по запросу клиента " +
                    "в приложении в случае, если карта имеет статус ACTIVE, тогда необходимо изменить её статус на " +
                    "BLOCKED, либо осуществить разблокировку, если статус карты - BLOCKED, с изменением её статуса на ACTIVE")
    public void block(@Valid @RequestBody ChangeCardStatusRequestDto creditCardDto) {
        final CreditCardEntity creditCardEntity = creditCardMapper.newStatusDtoToEntity(creditCardDto);
        creditCardService.block(creditCardEntity);
    }

    @PostMapping("/code")
    @Operation(summary = "15 - Маппинг Изменения PIN-кода кредитной карты",
            description = "В данном эндпоинте необходимо обновить PIN-код кредитной карты")
    public void pin(@Valid @RequestBody ChangePinCardRequestDto creditCardDto) {
        final CreditCardEntity creditCardEntity = creditCardMapper.newPinDtoToEntity(creditCardDto);
        creditCardService.pin(creditCardEntity);
    }

    @PatchMapping("/limit")
    @Operation(summary = "16 - Маппинг Установления лимита кредитной карты",
            description = "В данном эндпоинте необходимо установить новый лимит кредитной карты")
    public void limit(@RequestBody ChangeCardLimitRequestDto creditCardDto) {
        final CreditCardEntity creditCardEntity = creditCardMapper.newLimitDtoToEntity(creditCardDto);
        creditCardService.limit(creditCardEntity);
    }

    @GetMapping("/{cardId}")
    @Operation(summary = "10 - Отправка информации по кредитной карте",
            description = "В данном эндпоинте необходимо отправить информацию о карте, для последующего отображения")
    public CreditCardResponseDto getById(@PathVariable final String cardId) {
        CreditCardEntity creditCard = creditCardService.getById(cardId);
        return creditCardMapper.creditCardToDto(creditCard);
    }
}
