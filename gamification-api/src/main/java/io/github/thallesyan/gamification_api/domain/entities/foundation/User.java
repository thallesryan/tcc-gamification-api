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
}
