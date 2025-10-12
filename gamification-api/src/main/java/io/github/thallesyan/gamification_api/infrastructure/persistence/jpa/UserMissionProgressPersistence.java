package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.domain.entities.foundation.enums.ProgressStatusEnumJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.UserJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.MissionJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserMissionProgressJPA;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserMissionProgressPersistence extends JpaRepository<UserMissionProgressJPA, UUID> {
    Optional<UserMissionProgressJPA> findByUserAndMissionAndStatus(UserJPA user, MissionJPA mission, ProgressStatusEnumJPA progressStatusEnum);

    @Transactional
    @Modifying
    @Query("update UserMissionProgressJPA m set m.status = :progressStatusEnum where m.id = :userMissionId")
    Integer updateStatus(Integer userMissionId,  ProgressStatusEnumJPA progressStatusEnum);
}
