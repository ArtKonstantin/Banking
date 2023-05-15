package org.aston.credit.advice;

import jakarta.persistence.EntityNotFoundException;
import org.aston.credit.dto.responses.ErrorDto;
import org.aston.credit.dto.responses.ExceptionDto;
import org.aston.credit.dto.responses.ExceptionDtoForResponse;
import org.aston.credit.exception.BadCardStatusException;
import org.aston.credit.exception.BadRequestException;
import org.aston.credit.exception.CreditServiceBadRequestException;
import org.aston.credit.exception.CreditServiceNotFoundException;
import org.aston.credit.exception.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(CreditServiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto catchCreditServiceNotFoundException(CreditServiceNotFoundException e) {
        e.printStackTrace();
        return new ExceptionDto(new ExceptionDtoForResponse(e.getCode(), e.getDescription()));
    }

    @ResponseBody
    @ExceptionHandler(CreditServiceBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto catchBadRequestException(CreditServiceBadRequestException e) {
        e.printStackTrace();
        return new ExceptionDto(new ExceptionDtoForResponse(e.getCode(), e.getDescription()));
    }

    @ResponseBody
    @ExceptionHandler(BadCardStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto catchBadRequestException(BadCardStatusException e) {
        e.printStackTrace();
        return new ErrorDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto catchBadRequestException(BadRequestException e) {
        e.printStackTrace();
        return new ErrorDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorDto catchForbiddenException(ForbiddenException e) {
        e.printStackTrace();
        return new ErrorDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto catchNotFoundException(EntityNotFoundException e) {
        e.printStackTrace();
        return new ErrorDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto catchNoSuchElementException(NoSuchElementException e) {
        e.printStackTrace();
        return new ErrorDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(InternalError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto catchInternalServerException(InternalError e) {
        e.printStackTrace();
        return new ErrorDto(e.getMessage());
    }
}
