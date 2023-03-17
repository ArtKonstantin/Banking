package org.aston.credit.advice;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.aston.credit.dto.responses.ExceptionDto;
import org.aston.credit.dto.responses.ValidationErrorResponse;
import org.aston.credit.dto.responses.ViolationDto;
import org.aston.credit.exception.BadRequestException;
import org.aston.credit.exception.ForbiddenException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto catchBarRequestException(BadRequestException e) {
        e.printStackTrace();
        return new ExceptionDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionDto catchForbiddenException(ForbiddenException e) {
        e.printStackTrace();
        return new ExceptionDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto catchNotFoundException(EntityNotFoundException e) {
        e.printStackTrace();
        return new ExceptionDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto catchNoSuchElementException(NoSuchElementException e) {
        e.printStackTrace();
        return new ExceptionDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(InternalError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto catchInternalServerException(InternalError e) {
        e.printStackTrace();
        return new ExceptionDto(e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onConstraintValidationException(
            ConstraintViolationException e
    ) {
        final List<ViolationDto> violationDtos = e.getConstraintViolations().stream()
                .map(violation -> new ViolationDto(violation.getPropertyPath().toString(), violation.getMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violationDtos);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<ViolationDto> violationDtos = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new ViolationDto(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ValidationErrorResponse(violationDtos);
    }

}
