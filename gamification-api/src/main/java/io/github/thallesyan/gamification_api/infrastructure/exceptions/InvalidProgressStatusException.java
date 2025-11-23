package io.github.thallesyan.gamification_api.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidProgressStatusException extends BaseException {
    
    public InvalidProgressStatusException(String invalidStatus) {
        super(
            String.format("Invalid status: %s. Valid values: ASSIGNED, IN_PROGRESS, COMPLETED, FAILED, CANCELLED", invalidStatus),
            "INVALID_PROGRESS_STATUS",
            HttpStatus.BAD_REQUEST
        );
    }
}

