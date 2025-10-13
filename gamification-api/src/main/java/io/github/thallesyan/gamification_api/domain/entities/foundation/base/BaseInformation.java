package io.github.thallesyan.gamification_api.domain.entities.foundation.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseInformation {
    protected UUID identifier;
    protected String title;
    protected String description;

    protected BaseInformation(UUID identifier) {
        this.identifier = identifier;
    }
}
