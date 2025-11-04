package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BindUserMissionRequestDTO {

    @NotNull
    private String userEmail;
    @NotEmpty
    private Set<MissionBinding> missions;
}
