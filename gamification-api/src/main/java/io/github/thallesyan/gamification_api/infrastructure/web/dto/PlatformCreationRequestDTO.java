package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlatformCreationRequestDTO {
    @NotBlank
    private String name;
    private Integer progressBasePoints;
    private Double progressFormula;
}
