package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;

public interface AssociatePoints {
    Rarity associatePoints(Rarity rarity);
}

