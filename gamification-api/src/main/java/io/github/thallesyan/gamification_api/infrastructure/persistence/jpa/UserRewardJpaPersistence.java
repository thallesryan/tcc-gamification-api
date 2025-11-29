package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.UserJPA;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.UserRewardJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRewardJpaPersistence extends JpaRepository<UserRewardJPA, UUID> {
    List<UserRewardJPA> findByUser(UserJPA user);
    List<UserRewardJPA> findByUser_Identifier(UUID userIdentifier);
}

