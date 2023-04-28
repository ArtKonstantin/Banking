package org.aston.credit.exception;

public class BadCardBalanceException extends RuntimeException {
    public BadCardBalanceException(String message) {
        super(message);
    }
}
