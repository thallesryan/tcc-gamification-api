package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import io.github.thallesyan.gamification_api.infrastructure.web.dto.enums.UserIdentifierTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserIdentificationDTO {
    private UserIdentifierTypeEnum userIdentifierType;
    private String  userIdentifierValue;
}
