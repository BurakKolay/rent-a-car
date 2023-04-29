package com.burakkolay.rentacar.core.configuration.exceptions;

import com.burakkolay.rentacar.core.exceptions.BusinessException;
import com.burakkolay.rentacar.core.utils.results.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResult<BusinessException> handleBusinessException(BusinessException exception){
        return new ExceptionResult<>(BusinessException.class, exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResult<RuntimeException> handleRunTimeException(RuntimeException exception){
        return new ExceptionResult<>(RuntimeException.class, exception.getMessage());
    }
}
