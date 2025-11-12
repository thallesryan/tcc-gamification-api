package io.github.thallesyan.gamification_api.domain.entities.foundation;

import io.github.thallesyan.gamification_api.domain.entities.foundation.base.BaseReward;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reward extends BaseReward {
    private Integer points;
    private Badge badge;
}
