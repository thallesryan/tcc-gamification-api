package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequestDTO {
    private String clientId;
    private String clientSecret;
    private String grantType;
}

