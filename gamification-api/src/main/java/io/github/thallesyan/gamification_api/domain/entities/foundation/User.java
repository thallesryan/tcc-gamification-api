package io.github.thallesyan.gamification_api.domain.entities.foundation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private UUID identifier;
    private String name;
    private String email;
    private Date dateOfBirth;

    private User(UUID identifier) {
        this.identifier = identifier;
    }

    public static User byIdentifier(String identifier) {
        return new User(UUID.fromString(identifier));
    }

    public static User byIdentifier(UUID identifier) {
        return new User(identifier);
    }
}
