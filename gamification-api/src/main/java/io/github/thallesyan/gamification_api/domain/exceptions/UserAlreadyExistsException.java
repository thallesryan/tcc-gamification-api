package io.github.thallesyan.gamification_api.domain.exceptions;

import io.github.thallesyan.gamification_api.infrastructure.exceptions.BaseException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends BaseException {

    public UserAlreadyExistsException(String email) {
        super(String.format("User with email %s already exists", email),
                "USER_ALREADY_EXISTS",
                HttpStatus.CONFLICT);
    }
}

