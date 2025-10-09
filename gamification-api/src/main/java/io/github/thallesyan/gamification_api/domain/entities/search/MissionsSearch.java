package io.github.thallesyan.gamification_api.domain.entities.search;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import lombok.Data;

import java.util.*;

@Data
public class MissionsSearch {
    List<Mission> missionsFound = new ArrayList<>();
    List<UUID> missionsNotFound  = new ArrayList<>();

    public MissionsSearch(Map<UUID, Optional<Mission>> missions) {
        missions.forEach((uuid, mission) -> {
            if (mission.isPresent()) {
                missionsFound.add(mission.get());
            }else {
                missionsNotFound.add(uuid);
            }
        });
    }
}
