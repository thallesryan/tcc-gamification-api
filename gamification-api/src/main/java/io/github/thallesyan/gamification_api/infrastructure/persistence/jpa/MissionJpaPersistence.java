package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.MissionJPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MissionJpaPersistence extends JpaRepository<MissionJPA, UUID> {

    @Query("select m from MissionJPA m where m.identifier in (:missionIds)")
    List<MissionJPA> findMissiosByIdentifier(@Param("missionIds") List<UUID> missionIds);
}
