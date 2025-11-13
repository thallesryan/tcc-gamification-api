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

    @Transactional
    @Modifying
    @Query("update UserMissionProgressJPA m set m.status = :progressStatusEnum, m.startDate = now() where m.id = :userMissionId")
    Integer startUserMission(Integer userMissionId, ProgressStatusEnumJPA progressStatusEnum);

    List<UserMissionProgressJPA> findUserMissionProgressJPAByUserAndStatus(UserJPA user, ProgressStatusEnumJPA progressStatusEnum);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update UserMissionProgressJPA m set m.status = :progressStatusEnum, m.completionDate = now() where m.id = :userMissionId")
    Integer completeMission(Integer userMissionId, ProgressStatusEnumJPA progressStatusEnum);

    @Query(value = "SELECT ump.* FROM user_mission_progress ump " +
           "INNER JOIN users u ON ump.user_id = u.identifier " +
           "INNER JOIN platform p ON u.platform_name = p.name " +
           "INNER JOIN missions m ON ump.mission_id = m.identifier " +
           "WHERE m.identifier = UNHEX(REPLACE(:missionId, '-', '')) " +
           "AND p.name = :platformName " +
           "AND ump.status = :status " +
           "AND ump.start_date IS NOT NULL " +
           "AND ump.completion_date IS NOT NULL " +
           "ORDER BY TIMESTAMPDIFF(SECOND, ump.start_date, ump.completion_date) ASC",
           nativeQuery = true)
    List<UserMissionProgressJPA> findByMissionIdAndPlatformOrderedByCompletionTime(
            @Param("missionId") String missionId,
            @Param("platformName") String platformName,
            @Param("status") ProgressStatusEnumJPA status);

}
