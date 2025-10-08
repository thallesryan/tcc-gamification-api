package io.github.thallesyan.gamification_api.domain.entities.base;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseInformation {
    private UUID identifier;
    private String title;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime completeDate;
}
