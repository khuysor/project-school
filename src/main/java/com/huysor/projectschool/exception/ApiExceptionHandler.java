package com.huysor.projectschool.exception;

import com.huysor.projectschool.dto.response.ResultResp;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(
            value = {ApiRequestException.class}
    )
    public ResultResp handleApiException(ApiRequestException exception) {
        return ResultResp.fail();
    }

}
