package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.CreateRarityBoundary;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.PlatformJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.RarityJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.PlatformJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.reward.RarityJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.RarityPersistenceMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateRarityDb implements CreateRarityBoundary {

    private final RarityJpaPersistence rarityJpaPersistence;
    private final PlatformJpaPersistence platformJpaPersistence;
    private final RarityPersistenceMapper rarityPersistenceMapper;

    public CreateRarityDb(RarityJpaPersistence rarityJpaPersistence, 
                         PlatformJpaPersistence platformJpaPersistence,
                         RarityPersistenceMapper rarityPersistenceMapper) {
        this.rarityJpaPersistence = rarityJpaPersistence;
        this.platformJpaPersistence = platformJpaPersistence;
        this.rarityPersistenceMapper = rarityPersistenceMapper;
    }

    @Override
    public Rarity createOrUpdateRarity(Rarity rarity) {
        RarityJPA rarityJPA = rarityPersistenceMapper.toJPAEntity(rarity);

        if (rarityJPA.getPlatform() != null) {
            PlatformJPA platformJPA = platformJpaPersistence.findById(rarityJPA.getPlatform().getName())
                    .orElse(rarityJPA.getPlatform());
            rarityJPA.setPlatform(platformJPA);
        }
        
        return rarityPersistenceMapper.toModel(rarityJpaPersistence.save(rarityJPA));
    }
}

