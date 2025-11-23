package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import io.github.thallesyan.gamification_api.infrastructure.web.dto.enums.UserIdentifierTypeEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserIdentificationDTO {
    @NotNull(message = "User identifier type cannot be null")
    private UserIdentifierTypeEnum userIdentifierType;
    
    @NotNull(message = "User identifier value cannot be null")
    @NotBlank(message = "User identifier value cannot be blank")
    @Size(max = 255, message = "User identifier value must have at most 255 characters")
    private String userIdentifierValue;
}
