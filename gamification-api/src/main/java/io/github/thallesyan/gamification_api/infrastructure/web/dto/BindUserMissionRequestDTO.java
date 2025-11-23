package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BindUserMissionRequestDTO {

    @NotNull(message = "User email cannot be null")
    @NotBlank(message = "User email cannot be blank")
    @Email(message = "Email must have a valid format")
    @Size(max = 255, message = "Email must have at most 255 characters")
    private String userEmail;

    @NotNull(message = "Platform cannot be null")
    @NotBlank(message = "Platform cannot be blank")
    @Size(max = 255, message = "Platform must have at most 255 characters")
    private String platform;

    @NotNull(message = "Missions list cannot be null")
    @NotEmpty(message = "Missions list cannot be empty")
    @Valid
    private Set<MissionBinding> missions;
}
