package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.PlatformJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.reward.BadgeJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BadgeJpaPersistence extends JpaRepository<BadgeJPA, UUID> {
    
    @Query("SELECT b FROM BadgeJPA b WHERE b.rarity.nivel = :rarityEnum AND b.rarity.platform = :platform")
    List<BadgeJPA> findByRarityEnumAndPlatform(@Param("rarityEnum") RarityEnum rarityEnum, @Param("platform") PlatformJPA platform);
}

