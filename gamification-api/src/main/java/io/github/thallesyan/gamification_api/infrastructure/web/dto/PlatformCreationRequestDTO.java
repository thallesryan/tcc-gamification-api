package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlatformCreationRequestDTO {
    @NotNull(message = "Platform name cannot be null")
    @NotBlank(message = "Platform name cannot be blank")
    @Size(min = 1, max = 255, message = "Platform name must be between 1 and 255 characters")
    private String name;
    
    @Min(value = 0, message = "Progress base points must be at least 0")
    private Integer progressBasePoints;
    
    @Min(value = 0, message = "Progress formula must be at least 0")
    private Double progressFormula;
}
