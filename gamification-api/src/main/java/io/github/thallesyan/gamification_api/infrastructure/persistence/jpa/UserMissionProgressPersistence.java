package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.domain.entities.progress.enums.ProgressStatusEnumJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.UserJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.MissionJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserMissionProgressJPA;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMissionProgressPersistence extends JpaRepository<UserMissionProgressJPA, Integer> {

    Optional<UserMissionProgressJPA> findByUserAndMissionAndStatus(UserJPA user, MissionJPA mission, ProgressStatusEnumJPA progressStatusEnum);
    
    Optional<UserMissionProgressJPA> findByUserAndMission(UserJPA user, MissionJPA mission);

    @Transactional
    @Modifying
    @Query("update UserMissionProgressJPA m set m.status = :progressStatusEnum, m.startDate = now() where m.id = :userMissionId")
    Integer startUserMission(Integer userMissionId, ProgressStatusEnumJPA progressStatusEnum);

    List<UserMissionProgressJPA> findUserMissionProgressJPAByUserAndStatus(UserJPA user, ProgressStatusEnumJPA progressStatusEnum);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update UserMissionProgressJPA m set m.status = :progressStatusEnum, m.completionDate = now() where m.id = :userMissionId")
    Integer completeMission(Integer userMissionId, ProgressStatusEnumJPA progressStatusEnum);

    @Query("SELECT ump FROM UserMissionProgressJPA ump " +
           "WHERE ump.mission.identifier = :missionId " +
           "AND ump.user.platform.name = :platformName " +
           "AND ump.status = :status " +
           "AND ump.startDate IS NOT NULL " +
           "AND ump.completionDate IS NOT NULL")
    List<UserMissionProgressJPA> findByMissionIdAndPlatformOrderedByCompletionTime(
            @Param("missionId") java.util.UUID missionId,
            @Param("platformName") String platformName,
            @Param("status") ProgressStatusEnumJPA status);

    @Query("SELECT ump FROM UserMissionProgressJPA ump " +
           "WHERE ump.mission.identifier = :missionId " +
           "AND ump.status = :status " +
           "AND ump.startDate IS NOT NULL " +
           "AND ump.completionDate IS NOT NULL")
    List<UserMissionProgressJPA> findCompletedByMission(
            @Param("missionId") java.util.UUID missionId,
            @Param("status") ProgressStatusEnumJPA status);

}
