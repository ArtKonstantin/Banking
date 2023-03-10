package org.aston.credit.advice;

import jakarta.persistence.EntityNotFoundException;
import org.aston.credit.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto catchNotFoundException(EntityNotFoundException e) {
        e.printStackTrace();
        return new ExceptionDto("not found exception");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto catchNoSuchElementException(NoSuchElementException e) {
        e.printStackTrace();
        return new ExceptionDto("not found exception");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto catchInternalServerException(InternalError e) {
        e.printStackTrace();
        return new ExceptionDto("internal server exception");
    }
}
