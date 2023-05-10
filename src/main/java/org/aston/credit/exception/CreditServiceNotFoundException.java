package org.aston.credit.exception;

import lombok.Getter;

@Getter
public class CreditServiceNotFoundException extends RuntimeException {

    private final String code;
    private final String description;

    public CreditServiceNotFoundException(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
