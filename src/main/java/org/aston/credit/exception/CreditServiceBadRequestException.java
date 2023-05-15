package org.aston.credit.exception;

import lombok.Getter;

@Getter
public class CreditServiceBadRequestException extends RuntimeException {

    private final String code;
    private final String description;

    public CreditServiceBadRequestException(EnumCodeAndCommentException enumException) {
        this.code = enumException.getCode();
        this.description = enumException.getComment();
    }
}
