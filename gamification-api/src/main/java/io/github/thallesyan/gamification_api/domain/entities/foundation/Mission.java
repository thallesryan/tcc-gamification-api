package io.github.thallesyan.gamification_api.domain.entities.foundation;

import io.github.thallesyan.gamification_api.domain.entities.base.BaseInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mission extends BaseInformation {
    private List<Goal> goals;

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
