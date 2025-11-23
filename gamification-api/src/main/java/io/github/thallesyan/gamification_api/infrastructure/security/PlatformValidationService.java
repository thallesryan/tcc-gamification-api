package io.github.thallesyan.gamification_api.infrastructure.security;

import io.github.thallesyan.gamification_api.infrastructure.exceptions.PlatformNotAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Serviço para validação de acesso à plataforma baseado no JWT do Keycloak.
 * Verifica se a platform solicitada está presente no token JWT.
 */
@Service
@RequiredArgsConstructor
public class PlatformValidationService {

    public void validatePlatformAccess(String platform) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !(authentication.getPrincipal() instanceof Jwt jwt)) {
            throw new PlatformNotAuthorizedException(platform);
        }

        String jwtPlatform = jwt.getClaimAsString("platform");
        if (platform.equalsIgnoreCase(jwtPlatform)) {
            return;
        }

        throw new PlatformNotAuthorizedException(platform);
    }
}

