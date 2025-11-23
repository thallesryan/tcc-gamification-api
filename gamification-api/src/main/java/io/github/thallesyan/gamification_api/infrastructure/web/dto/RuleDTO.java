package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleDTO {
    @NotNull(message = "Rule name cannot be null")
    @NotBlank(message = "Rule name cannot be blank")
    @Size(min = 1, max = 255, message = "Rule name must be between 1 and 255 characters")
    private String name;
    
    private Duration durationLimit;
}

