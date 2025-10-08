package io.github.thallesyan.gamification_api.infrastructure.persistence.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users", 
       uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserJPA extends BaseEntityJPA {
    
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    
    @Column(name = "email", nullable = false, length = 255, unique = true)
    private String email;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;
}
