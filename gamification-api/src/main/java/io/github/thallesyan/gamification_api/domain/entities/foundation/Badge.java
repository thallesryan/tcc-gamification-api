package io.github.thallesyan.gamification_api.domain.entities.foundation;


import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Badge {
    private RarityEnum rarity;
}
