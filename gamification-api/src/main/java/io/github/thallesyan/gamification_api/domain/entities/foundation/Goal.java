package io.github.thallesyan.gamification_api.domain.entities.foundation;

import io.github.thallesyan.gamification_api.domain.entities.foundation.base.BaseInformation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Goal extends BaseInformation {
    private Integer order;

    public Goal(String title, String description, Integer order) {
        super();
        this.title = title;
        this.description = description;
        this.order = order;
    }
}
