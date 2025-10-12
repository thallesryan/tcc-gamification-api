package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserMissionGoalProgressJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserMissionGoalProgressPersistence extends JpaRepository<UserMissionGoalProgressJPA, UUID> {

    //UserMissionGoalProgressJPA  updateStatus(UserMissionGoalProgressJPA userMissionGoalProgressJPA, ProgressStatusEnum status);
}
