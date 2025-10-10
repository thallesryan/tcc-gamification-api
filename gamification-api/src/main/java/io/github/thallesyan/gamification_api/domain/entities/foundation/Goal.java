package io.github.thallesyan.gamification_api.domain.entities.foundation;

import io.github.thallesyan.gamification_api.domain.entities.base.BaseInformation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Goal extends BaseInformation {

    public Goal(String title, String description) {
        super();
        this.title = title;
        this.description = description;
    }
}
