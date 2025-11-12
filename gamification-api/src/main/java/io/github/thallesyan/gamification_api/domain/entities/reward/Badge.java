package io.github.thallesyan.gamification_api.domain.entities.reward;

import io.github.thallesyan.gamification_api.domain.entities.foundation.base.BaseReward;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Badge extends BaseReward {
    private Rarity rarity;

    public Badge(UUID identifier) {
        this.identifier = identifier;
    }

    public static Badge byIdentifier(String identifier) {
        return new Badge(UUID.fromString(identifier));
    }

    public static Badge byIdentifier(UUID identifier) {
        return new Badge(identifier);
    }
}
