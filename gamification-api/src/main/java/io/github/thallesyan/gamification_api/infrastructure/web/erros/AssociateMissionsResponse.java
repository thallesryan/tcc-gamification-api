package io.github.thallesyan.gamification_api.infrastructure.web.erros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociateMissionsResponse {
    private List<UUID> missionsAssociatedWithSuccess;
    private List<UUID> missionsNotFound;
}
