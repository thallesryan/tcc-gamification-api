package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.domain.services.CreateBadge;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.domain.services.FindBadge;
import io.github.thallesyan.gamification_api.domain.services.FindBadgesByRarity;
import io.github.thallesyan.gamification_api.domain.services.FindRarity;
import io.github.thallesyan.gamification_api.domain.services.UpdateBadge;
import io.github.thallesyan.gamification_api.application.exceptions.EntityNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BadgeApplication {

    private final CreateBadge createBadge;
    private final FindBadge findBadge;
    private final FindBadgesByRarity findBadgesByRarity;
    private final FindRarity findRarity;
    private final UpdateBadge updateBadge;

    public BadgeApplication(CreateBadge createBadge,
                            FindBadge findBadge,
                            FindBadgesByRarity findBadgesByRarity,
                            FindRarity findRarity,
                            UpdateBadge updateBadge) {
        this.createBadge = createBadge;
        this.findBadge = findBadge;
        this.findBadgesByRarity = findBadgesByRarity;
        this.findRarity = findRarity;
        this.updateBadge = updateBadge;
    }

    public Badge createBadge(Badge badge) {
        validateRarityExists(badge);
        return createBadge.createBadge(badge);
    }

    public List<Badge> findBadgesByRarity(RarityEnum rarityEnum, Platform platform) {
        return findBadgesByRarity.findBadgesByRarity(rarityEnum, platform);
    }

    public Optional<Badge> findBadgeById(UUID badgeId) {
        return findBadge.findBadgeById(badgeId);
    }

    public Badge updateBadge(UUID badgeId, Badge badge) {
        return updateBadge.updateBadge(badgeId, badge);
    }

    private void validateRarityExists(Badge badge) {
        Rarity rarity = badge.getRarity();
        findRarity.findByRarityEnumAndPlatform(rarity.getValue(), rarity.getPlatform())
                .orElseThrow(() -> new EntityNotFoundException("Rarity","value", rarity.getValue().name()));
    }
}

