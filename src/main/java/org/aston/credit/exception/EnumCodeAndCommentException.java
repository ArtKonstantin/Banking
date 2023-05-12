package org.aston.credit.exception;

import lombok.Getter;

@Getter
public enum EnumCodeAndCommentException {
    CREDIT_PRODUCT_NOT_ACTIVE("4054", "Кредитный продукт не активен"),
    AMOUNT_SHOULD_BE_WITHIN_MIN_AND_MAX("4017", "amountRequested должно быть в рамках min_sum и max_sum"),
    PERIOD_SHOULD_BE_WITHIN_MIN_AND_MAX("4018", "periodMonths должно быть в рамках min_period и max_period"),
    CREDIT_PRODUCT_NOT_FOUND("4055", "Кредитный продукт не найден");

    private final String code;
    private final String comment;

    EnumCodeAndCommentException(String code, String comment) {
        this.code = code;
        this.comment = comment;
    }
}
