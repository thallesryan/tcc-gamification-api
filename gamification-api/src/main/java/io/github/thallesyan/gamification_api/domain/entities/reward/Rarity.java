package io.github.thallesyan.gamification_api.domain.entities.reward;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rarity {
    private RarityEnum value;
    private Double points;
}
