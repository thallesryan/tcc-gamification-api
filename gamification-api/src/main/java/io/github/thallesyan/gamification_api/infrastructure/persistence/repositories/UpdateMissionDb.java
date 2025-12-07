package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.UpdateMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.MissionJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.MissionPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateMissionDb implements UpdateMissionBoundary {

    private final MissionJpaPersistence missionJpaPersistence;
    private final MissionPersistenceMapper missionPersistenceMapper;

    public UpdateMissionDb(MissionJpaPersistence missionJpaPersistence, 
                          MissionPersistenceMapper missionPersistenceMapper) {
        this.missionJpaPersistence = missionJpaPersistence;
        this.missionPersistenceMapper = missionPersistenceMapper;
    }

    @Override
    public Mission updateMission(UUID missionId, Mission mission, Integer difficultyLevel) {
        var existingMissionJPA = missionJpaPersistence.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Mission not found with id: " + missionId));

        // Atualizar apenas campos da missão (PATCH)
        if (mission.getTitle() != null) {
            existingMissionJPA.setTitle(mission.getTitle());
        }
        if (mission.getDescription() != null) {
            existingMissionJPA.setDescription(mission.getDescription());
        }
        if (mission.getPoints() != null) {
            existingMissionJPA.setPoints(mission.getPoints());
        }
        if (difficultyLevel != null) {
            existingMissionJPA.setDifficultyLevel(difficultyLevel);
        }

        var updatedMissionJPA = missionJpaPersistence.save(existingMissionJPA);
        return missionPersistenceMapper.toMission(updatedMissionJPA);
    }
}

