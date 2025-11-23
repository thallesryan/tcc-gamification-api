package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadgeCreationRequestDTO {

    @NotNull(message = "Badge name cannot be null")
    @NotBlank(message = "Badge name cannot be blank")
    @Size(min = 1, max = 255, message = "Badge name must be between 1 and 255 characters")
    private String name;

    @Size(max = 1000, message = "Badge description must have at most 1000 characters")
    private String description;

    @NotNull(message = "Rarity cannot be null")
    private RarityEnum rarity;
}

