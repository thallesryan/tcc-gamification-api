package io.github.thallesyan.gamification_api.domain.entities.foundation.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseReward {
    protected UUID identifier;
    protected String name;
    protected String description;
}
