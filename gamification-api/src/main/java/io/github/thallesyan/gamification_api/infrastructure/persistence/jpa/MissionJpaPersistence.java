package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.MissionJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MissionJpaPersistence extends JpaRepository<MissionJPA, UUID> {
}
