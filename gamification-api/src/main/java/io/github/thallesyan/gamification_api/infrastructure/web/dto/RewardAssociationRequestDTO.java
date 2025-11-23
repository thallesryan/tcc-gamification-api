package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardAssociationRequestDTO {
    @NotNull(message = "Reward identifier cannot be null")
    @NotBlank(message = "Reward identifier cannot be blank")
    @Size(max = 255, message = "Reward identifier must have at most 255 characters")
    private String identifier;
}
