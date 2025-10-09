package io.github.thallesyan.gamification_api;

import io.github.thallesyan.gamification_api.application.usecases.MissionApplication;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Goal;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.services.FindMission;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class First implements CommandLineRunner {

    private final MissionApplication missionApplication;
    private final FindMission findMission;

    public First(MissionApplication missionApplication, FindMission findMission) {
        this.missionApplication = missionApplication;
        this.findMission = findMission;
    }

    @Override
    public void run(String... args) throws Exception {
        var mission = missionApplication.createMission(new Mission("Teste Mission", "Teste mission desc", List.of(new Goal("Test 1", "Teste2"))));
        var missions = findMission.byMissionsIds(List.of(mission.getIdentifier(), UUID.fromString("1f386dd2-223d-4792-ad93-a0fb84b110ec")));
        System.out.println(missions);
    }
}
