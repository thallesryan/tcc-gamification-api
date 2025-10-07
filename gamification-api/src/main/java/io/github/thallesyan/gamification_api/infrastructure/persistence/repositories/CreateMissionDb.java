package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.CreateMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.MissionJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.MissionPersistenceMapper;
import org.springframework.stereotype.Service;

@Service
public class CreateMissionDb implements CreateMissionBoundary {

    private final MissionJpaPersistence missionJpaPersistence;
    private final MissionPersistenceMapper missionPersistenceMapper;

    public CreateMissionDb(MissionJpaPersistence missionJpaPersistence, MissionPersistenceMapper missionPersistenceMapper) {
        this.missionJpaPersistence = missionJpaPersistence;
        this.missionPersistenceMapper = missionPersistenceMapper;
    }

    @Override
    public Mission createMission(Mission mission) {
        var missionCreatedDb = missionJpaPersistence.save(missionPersistenceMapper.toMissionJPA(mission));
        return missionPersistenceMapper.toMission(missionCreatedDb);
    }
}
