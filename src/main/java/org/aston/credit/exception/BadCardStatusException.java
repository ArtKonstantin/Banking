package org.aston.credit.exception;

public class BadCardStatusException extends RuntimeException {
    public BadCardStatusException(String message) {
        super(message);
    }
}
