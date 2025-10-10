package io.github.thallesyan.gamification_api.infrastructure.persistence.repositories;

import io.github.thallesyan.gamification_api.domain.boundary.FindMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.MissionJpaPersistence;
import io.github.thallesyan.gamification_api.infrastructure.persistence.mappers.MissionPersistenceMapper;
import org.springframework.stereotype.Service;

import java.util.*;

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

    @Override
    public Map<UUID, Optional<Mission>> byMissionsIds(List<UUID> missionIds) {
        Map<UUID, Optional<Mission>> missionsByMissionId = new HashMap<>();
        var missions = missionJpaPersistence.findMissiosByIdentifier(missionIds);

        missionIds.forEach(id -> {
            missions.stream()
                    .filter(m -> m.getIdentifier().equals(id))
                    .findFirst()
                    .ifPresentOrElse(m -> {
                        missionsByMissionId.put(id, Optional.of(missionPersistenceMapper.toMission(m)));
                    }, () ->{
                        missionsByMissionId.put(id, Optional.empty());
                    });
        });

        return missionsByMissionId;
    }

}
