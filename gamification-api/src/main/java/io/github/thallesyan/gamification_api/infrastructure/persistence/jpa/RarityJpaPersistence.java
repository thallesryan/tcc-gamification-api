package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.PlatformJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.reward.RarityJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.reward.RarityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RarityJpaPersistence extends JpaRepository<RarityJPA, RarityId> {
    
    List<RarityJPA> findByPlatform(PlatformJPA platform);
    
    Optional<RarityJPA> findByNivelAndPlatform(RarityEnum nivel, PlatformJPA platform);
    
    boolean existsByNivelAndPlatform(RarityEnum nivel, PlatformJPA platform);
}

