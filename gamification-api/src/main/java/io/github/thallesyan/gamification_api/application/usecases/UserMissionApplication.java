package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException;
import io.github.thallesyan.gamification_api.domain.services.BindUserMissions;
import io.github.thallesyan.gamification_api.domain.services.FindMission;
import io.github.thallesyan.gamification_api.domain.services.FindUserByEmail;
import io.github.thallesyan.gamification_api.domain.services.VerifyUserExists;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserMissionApplication {

    private final FindMission findMission;
    private final VerifyUserExists verifyUserExists;
    private final FindUserByEmail findUserByEmail;
    private final BindUserMissions bindUserMissions;

    public UserMissionApplication(FindMission findMission, VerifyUserExists verifyUserExists, FindUserByEmail findUserByEmail, BindUserMissions bindUserMissions) {
        this.findMission = findMission;
        this.verifyUserExists = verifyUserExists;
        this.findUserByEmail = findUserByEmail;
        this.bindUserMissions = bindUserMissions;
    }

    //todo Criar retornos diferentes para caso todas missoes tenham sido associadas e quando so algumas estivetem
    //todo criar handler para excecoes
    public void associateUserMission(String userEmail, List<UUID> missionsIds) {
        var userOpt = findUserByEmail.byEmail(userEmail);
        if(userOpt.isEmpty()){
            throw new UserNotFoundException("User does not exist");
        }

        var missionsSearch = findMission.byMissionsIds(missionsIds);
        var missionsFound = missionsSearch.getMissionsFound();
        bindUserMissions.bindMissions(userOpt.get(), missionsFound);
    }
}
