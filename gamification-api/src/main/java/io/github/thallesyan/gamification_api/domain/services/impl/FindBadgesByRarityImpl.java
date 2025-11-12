package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindBadgeBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.domain.services.FindBadgesByRarity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindBadgesByRarityImpl implements FindBadgesByRarity {

    private final FindBadgeBoundary findBadgeBoundary;

    public FindBadgesByRarityImpl(FindBadgeBoundary findBadgeBoundary) {
        this.findBadgeBoundary = findBadgeBoundary;
    }

    @Override
    public List<Badge> findBadgesByRarity(RarityEnum rarityEnum, Platform platform) {
        return findBadgeBoundary.findBadgesByRarity(rarityEnum, platform);
    }
}

