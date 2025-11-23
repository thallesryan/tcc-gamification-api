package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindRankingBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.enums.RankingType;
import io.github.thallesyan.gamification_api.domain.services.FindRanking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testes para FindRankingImpl")
class FindRankingImplTest {

    @Mock
    private FindRankingBoundary findRankingBoundary;

    @InjectMocks
    private FindRankingImpl findRankingImpl;

    private List<UserProgress> userProgressList;

    @BeforeEach
    void setUp() {
        UserProgress userProgress1 = new UserProgress();
        userProgress1.setCurrentLevel(5);
        UserProgress userProgress2 = new UserProgress();
        userProgress2.setCurrentLevel(10);
        userProgressList = Arrays.asList(userProgress1, userProgress2);
    }

    @Test
    @DisplayName("Deve buscar ranking por plataforma e tipo")
    void deveBuscarRankingPorPlataformaETipo() {
        when(findRankingBoundary.findByPlatform("test-platform", RankingType.LEVEL))
                .thenReturn(userProgressList);

        List<UserProgress> result = findRankingImpl.findByPlatform("test-platform", RankingType.LEVEL);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(findRankingBoundary).findByPlatform("test-platform", RankingType.LEVEL);
    }

    @Test
    @DisplayName("Deve buscar ranking para diferentes tipos")
    void deveBuscarRankingParaDiferentesTipos() {
        when(findRankingBoundary.findByPlatform(anyString(), any(RankingType.class)))
                .thenReturn(userProgressList);

        findRankingImpl.findByPlatform("test-platform", RankingType.LEVEL);
        findRankingImpl.findByPlatform("test-platform", RankingType.GOALS_COMPLETED);
        findRankingImpl.findByPlatform("test-platform", RankingType.MISSION_COMPLETED);

        verify(findRankingBoundary, times(3)).findByPlatform(eq("test-platform"), any(RankingType.class));
    }

    @Test
    @DisplayName("Deve retornar lista vazia quando não houver ranking")
    void deveRetornarListaVaziaQuandoNaoHouverRanking() {
        when(findRankingBoundary.findByPlatform("test-platform", RankingType.LEVEL))
                .thenReturn(List.of());

        List<UserProgress> result = findRankingImpl.findByPlatform("test-platform", RankingType.LEVEL);

        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(findRankingBoundary).findByPlatform("test-platform", RankingType.LEVEL);
    }
}

