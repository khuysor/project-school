package com.huysor.projectschool.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(
            value = {ApiRequestException.class}
    )
    public ResponseEntity<Object> handleApiException(ApiRequestException exception) {
        ApiException apiException = new ApiException(exception.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }
}
