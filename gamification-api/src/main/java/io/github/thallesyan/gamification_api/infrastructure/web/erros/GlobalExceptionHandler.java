package io.github.thallesyan.gamification_api.infrastructure.web.erros;


import io.github.thallesyan.gamification_api.application.exceptions.AssociateMissionsException;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.infrastructure.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<?> NotFoundException(BaseException ex) {

        if (ex instanceof AssociateMissionsException associateMissionsException) {
            var idsMissionsFound = associateMissionsException.getMissionsSearch().getMissionsFound().stream().map(Mission::getIdentifier).toList();
            var idsMissionsAlreadyBondUser = associateMissionsException.getMissionsAlreadyExisted().stream().map(Mission::getIdentifier).toList();
            return new ResponseEntity<>(new AssociateMissionsResponse(idsMissionsFound, associateMissionsException.getMissionsSearch().getMissionsNotFound(), idsMissionsAlreadyBondUser), ex.getHttpStatus());
        }

        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<BeanValidationError>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        var errors = new ArrayList<BeanValidationError>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(new BeanValidationError(fieldName, errorMessage));
        });
        return new ResponseEntity<List<BeanValidationError>>(errors, HttpStatus.BAD_REQUEST);
    }
}