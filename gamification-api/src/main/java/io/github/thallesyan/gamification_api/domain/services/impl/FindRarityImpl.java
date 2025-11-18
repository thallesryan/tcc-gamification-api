package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindRarityBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.domain.services.FindRarity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FindRarityImpl implements FindRarity {

    private final FindRarityBoundary findRarityBoundary;

    public FindRarityImpl(FindRarityBoundary findRarityBoundary) {
        this.findRarityBoundary = findRarityBoundary;
    }

    @Override
    public List<Rarity> findByPlatform(Platform platform) {
        return findRarityBoundary.findByPlatform(platform);
    }

    @Override
    public Optional<Rarity> findByRarityEnumAndPlatform(RarityEnum rarityEnum, Platform platform) {
        return findRarityBoundary.findByRarityEnumAndPlatform(rarityEnum, platform);
    }
}

