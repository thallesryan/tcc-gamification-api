package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.PlatformJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PlatformJpaPersistence extends JpaRepository<PlatformJPA, String> {
}
