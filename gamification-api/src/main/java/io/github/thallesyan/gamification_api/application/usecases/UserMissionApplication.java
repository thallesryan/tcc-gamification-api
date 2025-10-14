package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.services.*;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserMissionApplication {

    private final FindMission findMission;
    private final FindUserByEmail findUserByEmail;
    private final BindUserMissions bindUserMissions;
    private final FindUserMission findUserMissionByIdentifier;
    private final FindUserMission findUserMissionByEmail;
    private final UpdateUserMission updateUserMission;

    public UserMissionApplication(FindMission findMission, FindUserByEmail findUserByEmail, BindUserMissions bindUserMissions, @Qualifier("findUserMissionByUserIdentifierImpl") FindUserMission findUserMissionByIdentifier, @Qualifier("findUserMissionByUserEmailImpl") FindUserMission findUserMissionByEmail, UpdateUserMission updateUserMission) {
        this.findMission = findMission;
        this.findUserByEmail = findUserByEmail;
        this.bindUserMissions = bindUserMissions;
        this.findUserMissionByIdentifier = findUserMissionByIdentifier;
        this.findUserMissionByEmail = findUserMissionByEmail;
        this.updateUserMission = updateUserMission;
    }

    //todo Criar retornos diferentes para caso todas missoes tenham sido associadas e quando so algumas estivetem. Ex em casos de missoes nao encontradas pelos ids
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

    // todo get user missions actived, in progress etc


    public UserMissionProgress startMissionByUserEmail(String userEmail, String missionId) {
        var userMission = findUserMissionByEmail.byMissionIdAndStatus(userEmail, missionId, ProgressStatusEnum.ASSIGNED);
        return updateUserMission.startMission(userMission);
    }

    public UserMissionProgress startMissionByUserIdentifier(String userIdentifier, String missionId) {
        var userMission = findUserMissionByIdentifier.byMissionIdAndStatus(userIdentifier, missionId, ProgressStatusEnum.ASSIGNED);
        return updateUserMission.startMission(userMission);
    }
}
