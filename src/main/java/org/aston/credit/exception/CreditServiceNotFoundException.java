package org.aston.credit.exception;

import lombok.Getter;

@Getter
public class CreditServiceNotFoundException extends RuntimeException {

    private final String code;
    private final String description;

    public CreditServiceNotFoundException(EnumCodeAndCommentException enumException) {
        this.code = enumException.getCode();
        this.description = enumException.getComment();
    }
}
