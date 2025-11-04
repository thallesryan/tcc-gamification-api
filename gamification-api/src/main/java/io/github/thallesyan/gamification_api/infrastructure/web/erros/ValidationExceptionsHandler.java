//package io.github.thallesyan.gamification_api.infrastructure.web.erros;
//
//import io.github.thallesyan.gamification_api.application.exceptions.UserMissionNotFound;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//import java.util.List;
//
//@ControllerAdvice
//public class ValidationExceptionsHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler({
//            MethodArgumentNotValidException.class
//    })
//    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e){
//        StringBuilder message = new StringBuilder();
//        for(FieldError field: e.getBindingResult().getFieldErrors()){
//           message.append(field.getDefaultMessage()).append(" ");
//        }
//        return new ResponseEntity<>(new ErrorResponse(message.toString()), HttpStatus.BAD_REQUEST);
//    }
//
//
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @ExceptionHandler(UserMissionNotFound.class)
//    public ResponseEntity<ErrorResponse> handleException(UserMissionNotFound ex, WebRequest request) {
//        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
//    }
//}