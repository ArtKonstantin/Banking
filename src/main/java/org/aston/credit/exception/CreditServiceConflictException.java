package org.aston.credit.exception;

import lombok.Getter;

@Getter
public class CreditServiceConflictException extends RuntimeException {
    private final String code;
    private final String description;

    public CreditServiceConflictException(EnumCodeAndCommentException enumException) {
        this.code = enumException.getCode();
        this.description = enumException.getComment();
    }
}
