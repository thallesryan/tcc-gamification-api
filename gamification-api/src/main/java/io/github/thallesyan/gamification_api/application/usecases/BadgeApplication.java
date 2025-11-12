package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.domain.services.CreateBadge;
import io.github.thallesyan.gamification_api.domain.services.FindBadgesByRarity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BadgeApplication {

    private final CreateBadge createBadge;
    private final FindBadgesByRarity findBadgesByRarity;

    public BadgeApplication(CreateBadge createBadge, FindBadgesByRarity findBadgesByRarity) {
        this.createBadge = createBadge;
        this.findBadgesByRarity = findBadgesByRarity;
    }

    public Badge createBadge(Badge badge) {
        return createBadge.createBadge(badge);
    }

    public List<Badge> findBadgesByRarity(RarityEnum rarityEnum, Platform platform) {
        return findBadgesByRarity.findBadgesByRarity(rarityEnum, platform);
    }
}

