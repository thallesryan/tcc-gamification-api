package io.github.thallesyan.gamification_api.infrastructure.web.erros;

import io.github.thallesyan.gamification_api.application.exceptions.UserMissionNotFound;
import io.github.thallesyan.gamification_api.infrastructure.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ErrorResponse> handleException(BaseException ex, WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), ex.getHttpStatus());
    }
}
