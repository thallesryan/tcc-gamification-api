package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "users", 
       uniqueConstraints = {@UniqueConstraint(columnNames = { "platform_name", "email" })})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserJPA extends BaseEntityJPA {

    public UserJPA(UUID identifier) {
        super(identifier);
    }

    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @ManyToOne(cascade = {})
    @JoinColumn(name = "platform_name")
    private PlatformJPA platform;

    public static UserJPA byIdentifier(String identifier){
        return new UserJPA(UUID.fromString(identifier));
    }

    public static UserJPA byIdentifier(UUID identifier){
        return new UserJPA(identifier);
    }
}
