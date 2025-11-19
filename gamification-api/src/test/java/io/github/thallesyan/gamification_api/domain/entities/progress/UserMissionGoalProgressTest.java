package io.github.thallesyan.gamification_api.domain.entities.progress;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para UserMissionGoalProgress")
class UserMissionGoalProgressTest {

    @Test
    @DisplayName("Deve criar UserMissionGoalProgress vazio com construtor padrão")
    void deveCriarUserMissionGoalProgressVazio() {
        UserMissionGoalProgress userMissionGoalProgress = new UserMissionGoalProgress();

        assertNull(userMissionGoalProgress.getGoal());
        assertNull(userMissionGoalProgress.getId());
        assertNull(userMissionGoalProgress.getStatus());
    }

    @Test
    @DisplayName("Deve criar UserMissionGoalProgress com goal")
    void deveCriarUserMissionGoalProgressComGoal() {
        Goal goal = new Goal("Goal Title", "Goal Description", 1);

        UserMissionGoalProgress userMissionGoalProgress = new UserMissionGoalProgress();
        userMissionGoalProgress.setGoal(goal);

        assertEquals(goal, userMissionGoalProgress.getGoal());
    }
}

