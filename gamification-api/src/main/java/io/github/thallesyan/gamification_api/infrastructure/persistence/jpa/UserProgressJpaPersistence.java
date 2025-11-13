package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.PlatformJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.UserProgressJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserProgressJpaPersistence extends JpaRepository<UserProgressJPA, UUID> {
    java.util.Optional<UserProgressJPA> findByUser_Identifier(UUID userIdentifier);
    
    @Query("SELECT up FROM UserProgressJPA up WHERE up.user.platform = :platform ORDER BY up.totalPoints DESC")
    List<UserProgressJPA> findByPlatformOrderByPoints(@Param("platform") PlatformJPA platform);
    
    @Query("SELECT up FROM UserProgressJPA up WHERE up.user.platform = :platform ORDER BY up.goalsCompleted DESC")
    List<UserProgressJPA> findByPlatformOrderByGoalsCompleted(@Param("platform") PlatformJPA platform);
    
    @Query("SELECT up FROM UserProgressJPA up WHERE up.user.platform = :platform ORDER BY up.missionsCompleted DESC")
    List<UserProgressJPA> findByPlatformOrderByMissionsCompleted(@Param("platform") PlatformJPA platform);
}

