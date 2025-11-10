package io.github.thallesyan.gamification_api.domain.entities.reward;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rarity {
    private RarityEnum value;
    private Integer points;
    private Platform platform;
}
