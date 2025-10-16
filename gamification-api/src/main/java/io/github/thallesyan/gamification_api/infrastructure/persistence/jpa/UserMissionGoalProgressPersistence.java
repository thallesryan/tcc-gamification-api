package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.domain.entities.progress.enums.ProgressStatusEnumJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserMissionGoalProgressJPA;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMissionGoalProgressPersistence extends JpaRepository<UserMissionGoalProgressJPA, Integer> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update UserMissionGoalProgressJPA m set m.status = :progressStatusEnum, m.startDate = now() where m.id = :userMissionGoalId")
    Integer startUserMissionGoal(Integer userMissionGoalId, ProgressStatusEnumJPA progressStatusEnum);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("update UserMissionGoalProgressJPA m set m.status = :progressStatusEnum, m.completionDate = now() where m.id = :userMissionGoalId")
    Integer completeMissionGoal(Integer userMissionGoalId, ProgressStatusEnumJPA progressStatusEnum);
}
