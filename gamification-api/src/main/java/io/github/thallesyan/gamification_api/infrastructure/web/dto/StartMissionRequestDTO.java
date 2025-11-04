package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartMissionRequestDTO {
    @NotNull
    private UserIdentificationDTO userIdentification;
    @NotNull
    private String missionIdentifier;
}
