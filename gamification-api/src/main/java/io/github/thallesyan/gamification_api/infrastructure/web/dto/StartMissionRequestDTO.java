package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartMissionRequestDTO {
    @NotNull(message = "User identification cannot be null")
    @Valid
    private UserIdentificationDTO userIdentification;
    
    @NotNull(message = "Mission identifier cannot be null")
    @NotBlank(message = "Mission identifier cannot be blank")
    @Size(max = 255, message = "Mission identifier must have at most 255 characters")
    private String missionIdentifier;
}
