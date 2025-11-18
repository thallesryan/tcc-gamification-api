package io.github.thallesyan.gamification_api.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidUuidException extends BaseException{
    public InvalidUuidException(String invalidUuid) {
        super(String.format("%s is not a valid UUID", invalidUuid), "INVALID_UUID", HttpStatus.BAD_REQUEST);
    }
}
