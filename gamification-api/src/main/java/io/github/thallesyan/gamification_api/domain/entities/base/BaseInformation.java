package io.github.thallesyan.gamification_api.domain.entities.base;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
public abstract class BaseInformation {
    protected UUID identifier;
    protected String title;
    protected String description;
}
