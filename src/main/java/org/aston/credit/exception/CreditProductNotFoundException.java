package org.aston.credit.exception;

import lombok.Getter;

@Getter
public class CreditProductNotFoundException extends RuntimeException {

    private String code;
    private String description;

    public CreditProductNotFoundException() {
        this.code = "4054";
        this.description = "Кредитный продукт не активен";
    }
}
