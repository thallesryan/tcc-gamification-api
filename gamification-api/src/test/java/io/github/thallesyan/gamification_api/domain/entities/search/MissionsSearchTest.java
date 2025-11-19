package io.github.thallesyan.gamification_api.domain.entities.search;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para MissionsSearch")
class MissionsSearchTest {

    @Test
    @DisplayName("Deve criar MissionsSearch separando missions encontradas e não encontradas")
    void deveCriarMissionsSearchSeparandoMissions() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        UUID id3 = UUID.randomUUID();

        Mission mission1 = new Mission(id1);
        Mission mission2 = new Mission(id2);

        Map<UUID, Optional<Mission>> missions = new HashMap<>();
        missions.put(id1, Optional.of(mission1));
        missions.put(id2, Optional.of(mission2));
        missions.put(id3, Optional.empty());

        MissionsSearch missionsSearch = new MissionsSearch(missions);

        assertEquals(2, missionsSearch.getMissionsFound().size());
        assertEquals(1, missionsSearch.getMissionsNotFound().size());
        assertTrue(missionsSearch.getMissionsFound().contains(mission1));
        assertTrue(missionsSearch.getMissionsFound().contains(mission2));
        assertTrue(missionsSearch.getMissionsNotFound().contains(id3));
    }

    @Test
    @DisplayName("Deve criar MissionsSearch apenas com missions encontradas")
    void deveCriarMissionsSearchApenasComMissionsEncontradas() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Mission mission1 = new Mission(id1);
        Mission mission2 = new Mission(id2);

        Map<UUID, Optional<Mission>> missions = new HashMap<>();
        missions.put(id1, Optional.of(mission1));
        missions.put(id2, Optional.of(mission2));

        MissionsSearch missionsSearch = new MissionsSearch(missions);

        assertEquals(2, missionsSearch.getMissionsFound().size());
        assertEquals(0, missionsSearch.getMissionsNotFound().size());
    }

    @Test
    @DisplayName("Deve criar MissionsSearch apenas com missions não encontradas")
    void deveCriarMissionsSearchApenasComMissionsNaoEncontradas() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();

        Map<UUID, Optional<Mission>> missions = new HashMap<>();
        missions.put(id1, Optional.empty());
        missions.put(id2, Optional.empty());

        MissionsSearch missionsSearch = new MissionsSearch(missions);

        assertEquals(0, missionsSearch.getMissionsFound().size());
        assertEquals(2, missionsSearch.getMissionsNotFound().size());
        assertTrue(missionsSearch.getMissionsNotFound().contains(id1));
        assertTrue(missionsSearch.getMissionsNotFound().contains(id2));
    }

    @Test
    @DisplayName("Deve criar MissionsSearch vazio quando map estiver vazio")
    void deveCriarMissionsSearchVazio() {
        Map<UUID, Optional<Mission>> missions = new HashMap<>();

        MissionsSearch missionsSearch = new MissionsSearch(missions);

        assertEquals(0, missionsSearch.getMissionsFound().size());
        assertEquals(0, missionsSearch.getMissionsNotFound().size());
    }
}

