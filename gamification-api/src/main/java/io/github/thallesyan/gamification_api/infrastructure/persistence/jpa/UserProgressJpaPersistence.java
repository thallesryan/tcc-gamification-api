package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserProgressJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserProgressJpaPersistence extends JpaRepository<UserProgressJPA, UUID> {
    java.util.Optional<UserProgressJPA> findByUser_Identifier(UUID userIdentifier);
}

