package io.github.thallesyan.gamification_api.domain.entities.foundation;

import io.github.thallesyan.gamification_api.domain.entities.foundation.base.BaseReward;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;


@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Rule extends BaseReward {
private Duration durationLimit;
}
