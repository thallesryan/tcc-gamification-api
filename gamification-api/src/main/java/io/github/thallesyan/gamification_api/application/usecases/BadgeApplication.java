package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.domain.services.CreateBadge;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.services.FindBadgesByRarity;
import io.github.thallesyan.gamification_api.domain.services.FindRarity;
import io.github.thallesyan.gamification_api.application.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BadgeApplication {

    private final CreateBadge createBadge;
    private final FindBadgesByRarity findBadgesByRarity;
    private final FindRarity findRarity;

    public BadgeApplication(CreateBadge createBadge,
                            FindBadgesByRarity findBadgesByRarity,
                            FindRarity findRarity) {
        this.createBadge = createBadge;
        this.findBadgesByRarity = findBadgesByRarity;
        this.findRarity = findRarity;
    }

    public Badge createBadge(Badge badge) {
        validateRarityExists(badge);
        return createBadge.createBadge(badge);
    }

    public List<Badge> findBadgesByRarity(RarityEnum rarityEnum, Platform platform) {
        return findBadgesByRarity.findBadgesByRarity(rarityEnum, platform);
    }

    private void validateRarityExists(Badge badge) {
        Rarity rarity = badge.getRarity();
        findRarity.findByRarityEnumAndPlatform(rarity.getValue(), rarity.getPlatform())
                .orElseThrow(() -> new EntityNotFoundException("Rarity","value", rarity.getValue().name()));
    }
}

