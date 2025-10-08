package io.github.thallesyan.gamification_api.infrastructure.exceptions;

import java.util.UUID;

public class EntityNotFoundException extends BaseException {
    
    public EntityNotFoundException(String entityName, UUID id) {
        super(String.format("%s with id %s not found", entityName, id), 
              "ENTITY_NOT_FOUND");
    }
    
    public EntityNotFoundException(String entityName, String field, String value) {
        super(String.format("%s with %s %s not found", entityName, field, value), 
              "ENTITY_NOT_FOUND");
    }
}

