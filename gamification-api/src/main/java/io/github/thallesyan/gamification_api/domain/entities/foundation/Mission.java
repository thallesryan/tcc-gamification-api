package io.github.thallesyan.gamification_api.domain.entities.foundation;

import io.github.thallesyan.gamification_api.domain.entities.foundation.base.BaseInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mission extends BaseInformation {
    private Duration estimatedDuration;
    private Integer points;
    private List<Goal> goals;
    private Rule rule;
    private Reward reward;

    public Mission(String title, String description, List<Goal> goals) {
        super();
        this.title = title;
        this.description = description;
        this.goals = goals;
    }

    public Mission(UUID identifier) {
        super(identifier);
    }

    public static Mission byIdentifier(String identifier) {
        return new Mission(UUID.fromString(identifier));
    }

    public static Mission byIdentifier(UUID identifier) {
        return new Mission(identifier);
    }
}
