package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MissionBinding {
    @NotNull(message = "Mission identifier cannot be null")
    @NotBlank(message = "Mission identifier cannot be blank")
    @Size(max = 255, message = "Mission identifier must have at most 255 characters")
    private String identifier;
}
