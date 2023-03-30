package org.aston.credit.dto.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;

@Value
@Builder
@Schema(description = "Запрос на создание заявки на кредит")
public class CreditOrderRequestDto {

    @Schema(description = "id кредитного продукта", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Id кредитного продукта не может быть пустым")
    @Pattern(regexp = "\\d", message = "Id кредитного продукта должен быть числом")
    Long productId;

    @Schema(description = "сумма кредита", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "сумма кредита не может быть пустой")
    @Pattern(regexp = "\\d.\\d", message = "сумма кредита должна быть числом формата 00.00")
    @Min(value = 0, message = "сумма кредита не может быть меньше 0")
    BigDecimal amountRequested;

    @Schema(description = "срок кредита в месяцах", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "срок кредита не может быть пустым")
    @Pattern(regexp = "\\d", message = "срок кредита должна быть целым числом")
    @Min(value = 0, message = "срок кредита не может быть меньше 0")
    Integer periodMonths;

    //TODO: Стоит ли передавать дату создания заявки в теле? Мб просто брать LD.now()?
    //TODO: Узнать у аналитиков минимальные значения для числовых параметров? И в целом какие им нужны ограничения?
    @Schema(description = "дата создания заявки", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "дата создания заявки не может быть пустой")
    LocalDate creationDate;

    @Schema(description = "месячный доход клиента", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "месячный доход клиента не может быть пустым")
    @Pattern(regexp = "\\d.\\d", message = "месячный доход клиента должен быть числом формата 00.00")
    @Min(value = 0, message = "месячный доход клиента не может быть меньше 0")
    BigDecimal monthlyIncome;

    @Schema(description = "ежемесячная долговая нагрузка клиента", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "ежемесячная долговая нагрузка клиента не может быть пустым")
    @Pattern(regexp = "\\d.\\d", message = "ежемесячная долговая нагрузка клиента должен быть числом формата 00.00")
    @Min(value = 0, message = "ежемесячная долговая нагрузка клиента не может быть меньше 0")
    BigDecimal monthlyExpenditure;

    @Schema(description = "ИНН клиента", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "ИНН клиента не может быть пустым")
    @Pattern(regexp = "\\d{12}", message = "ИНН клиента должен состоять из целых чисел")
    String employerIdentificationNumber;
}
