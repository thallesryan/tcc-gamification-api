package io.github.thallesyan.gamification_api.application.exceptions;

import io.github.thallesyan.gamification_api.infrastructure.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super("User not found", "USER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }

    public UserNotFoundException(String message) {
        super(message, "USER_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}
