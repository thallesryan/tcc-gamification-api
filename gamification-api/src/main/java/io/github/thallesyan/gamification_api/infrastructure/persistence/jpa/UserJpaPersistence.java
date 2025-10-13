package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.UserJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaPersistence extends JpaRepository<UserJPA, UUID> {
    
    Optional<UserJPA> findByEmail(String email);
    
    boolean existsByEmail(String email);
}
