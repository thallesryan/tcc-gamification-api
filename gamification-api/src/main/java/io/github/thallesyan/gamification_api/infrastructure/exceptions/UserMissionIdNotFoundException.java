package io.github.thallesyan.gamification_api.infrastructure.exceptions;

import org.springframework.http.HttpStatus;

public class UserMissionIdNotFoundException extends BaseException {
    
    public UserMissionIdNotFoundException(Integer id) {
        super(String.format("User mission with id %d not found", id), 
              "USER_MISSION_ID_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
    
    public UserMissionIdNotFoundException(String message) {
        super(message, "USER_MISSION_ID_NOT_FOUND", HttpStatus.NOT_FOUND);
    }
}

