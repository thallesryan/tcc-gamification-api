package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.MissionJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.MissionPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindMissionDb implements FindMissionBoundary {

    private final MissionJpaPersistence missionJpaPersistence;
    private final MissionPersistenceMapper missionPersistenceMapper;

    public FindMissionDb(MissionJpaPersistence missionJpaPersistence, MissionPersistenceMapper missionPersistenceMapper) {
        this.missionJpaPersistence = missionJpaPersistence;
        this.missionPersistenceMapper = missionPersistenceMapper;
    }

    @Override
    public Optional<Mission> findMissionById(UUID missionId) {
        return missionJpaPersistence.findById(missionId)
                .map(missionPersistenceMapper::toMission);
    }
}
