package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardCreationRequestDTO {
    private String identifier;
    private String name;
    private String description;
    private Integer points;

    @Valid
    private BadgeCreationRequestDTO badge;
}

