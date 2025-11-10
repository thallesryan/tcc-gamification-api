package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindRarityBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.RarityJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.PlatformJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.PlatformPersistenceMapper;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.RarityPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FindRarityDb implements FindRarityBoundary {

    private final RarityJpaPersistence rarityJpaPersistence;
    private final RarityPersistenceMapper rarityPersistenceMapper;
    private final PlatformPersistenceMapper platformPersistenceMapper;

    public FindRarityDb(RarityJpaPersistence rarityJpaPersistence, 
                       RarityPersistenceMapper rarityPersistenceMapper,
                       PlatformPersistenceMapper platformPersistenceMapper) {
        this.rarityJpaPersistence = rarityJpaPersistence;
        this.rarityPersistenceMapper = rarityPersistenceMapper;
        this.platformPersistenceMapper = platformPersistenceMapper;
    }

    @Override
    public List<Rarity> findByPlatform(Platform platform) {
        PlatformJPA platformJPA = platformPersistenceMapper.toJPAEntity(platform);
        return rarityJpaPersistence.findByPlatform(platformJPA).stream()
                .map(rarityPersistenceMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Rarity> findByRarityEnumAndPlatform(io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum rarityEnum, Platform platform) {
        PlatformJPA platformJPA = platformPersistenceMapper.toJPAEntity(platform);
        return rarityJpaPersistence.findByNivelAndPlatform(rarityEnum, platformJPA)
                .map(rarityPersistenceMapper::toModel);
    }
}

