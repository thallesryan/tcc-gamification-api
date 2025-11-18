package io.github.thallesyan.gamification_api.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class CurrentGoalNotFoundException extends BaseException {
    
    public CurrentGoalNotFoundException() {
        super("Mission must be started", "CURRENT_GOAL_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
    
    public CurrentGoalNotFoundException(String message) {
        super(message, "CURRENT_GOAL_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}

