package io.github.thallesyan.gamification_api.infrastructure.web.erros;

import io.github.thallesyan.gamification_api.application.exceptions.AssociateMissionsException;
import io.github.thallesyan.gamification_api.application.exceptions.UserMissionNotFound;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
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
    public ResponseEntity<?> handleException(BaseException ex, WebRequest request) {

        if(ex instanceof AssociateMissionsException associateMissionsException){
            var idsMissionsFound = associateMissionsException.getMissionsSearch().getMissionsFound().stream().map(Mission::getIdentifier).toList();
            return new ResponseEntity<>(new AssociateMissionsResponse(idsMissionsFound, associateMissionsException.getMissionsSearch().getMissionsNotFound()), ex.getHttpStatus());
        }

        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), ex.getHttpStatus());
    }
}
