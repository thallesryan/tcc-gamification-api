package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.AssociateMissionsException;
import io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionGoalProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.services.*;
import io.github.thallesyan.gamification_api.infrastructure.exceptions.CurrentGoalNotFoundException;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class UserMissionApplication {

    private final FindMission findMission;
    private final FindUserByEmail findUserByEmail;
    private final BindUserMissions bindUserMissions;
    private final FindUserMission findUserMissionByIdentifier;
    private final FindUserMission findUserMissionByEmail;
    private final FindUserMissionById findUserMissionById;
    private final UpdateUserMission updateUserMission;
    private final FindUserProgressMissionsByStatusAndPlatform findUserProgressMissionsByStatusAndPlatform;
    private final FindUserProgress findUserProgress;
    private final UpdateUserProgress updateUserProgress;
    private final UpdateMissionEstimatedDuration updateMissionEstimatedDuration;
    private final AssignRewardToUser assignRewardToUser;
    private final UserProgressPoints userProgressPoints;

    public UserMissionApplication(FindMission findMission, FindUserByEmail findUserByEmail, BindUserMissions bindUserMissions, @Qualifier("findUserMissionByUserIdentifierImpl") FindUserMission findUserMissionByIdentifier, @Qualifier("findUserMissionByUserEmailImpl") FindUserMission findUserMissionByEmail, FindUserMissionById findUserMissionById, UpdateUserMission updateUserMission, FindUserProgressMissionsByStatusAndPlatform findUserProgressMissionsByStatusAndPlatform, FindUserProgress findUserProgress, UpdateUserProgress updateUserProgress, UpdateMissionEstimatedDuration updateMissionEstimatedDuration, AssignRewardToUser assignRewardToUser, UserProgressPoints userProgressPoints) {
        this.findMission = findMission;
        this.findUserByEmail = findUserByEmail;
        this.bindUserMissions = bindUserMissions;
        this.findUserMissionByIdentifier = findUserMissionByIdentifier;
        this.findUserMissionByEmail = findUserMissionByEmail;
        this.findUserMissionById = findUserMissionById;
        this.updateUserMission = updateUserMission;
        this.findUserProgressMissionsByStatusAndPlatform = findUserProgressMissionsByStatusAndPlatform;
        this.findUserProgress = findUserProgress;
        this.updateUserProgress = updateUserProgress;
        this.updateMissionEstimatedDuration = updateMissionEstimatedDuration;
        this.assignRewardToUser = assignRewardToUser;
        this.userProgressPoints = userProgressPoints;
    }

    //todo Criar retornos diferentes para caso todas missoes tenham sido associadas e quando so algumas estivetem. Ex em casos de missoes nao encontradas pelos ids
    public void associateUserMission(String userEmail, String platform, List<UUID> missionsIds) {
        var user = findUserByEmail.byEmail(userEmail, platform).orElseThrow(UserNotFoundException::new);
        var missionsSearch = findMission.byMissionsIds(missionsIds);

        if(!missionsSearch.getMissionsFound().isEmpty()){
            var missionsFound = missionsSearch.getMissionsFound();
            bindUserMissions.bindMissions(user, missionsFound);
        }

        if (!missionsSearch.getMissionsNotFound().isEmpty()){
            throw new AssociateMissionsException(missionsSearch);
        }
    }

    //todo quando for utilizado email, terá que passar platform também
    //todo tornar user mission e progress unicos (só poderá ter uma missao com o id em progress, n deixar cadastrar outra)
    public UserMissionProgress startMissionByUserEmail(String userEmail, String platform, String missionId) {
        var userMission = findUserMissionByEmail.byMissionIdAndStatus(userEmail, platform, missionId, ProgressStatusEnum.ASSIGNED);
        return updateUserMission.startMission(userMission);
    }

    public UserMissionProgress startMissionByUserIdentifier(String userIdentifier, String platform, String missionId) {
        var userMission = findUserMissionByIdentifier.byMissionIdAndStatus(userIdentifier, platform, missionId, ProgressStatusEnum.ASSIGNED);
        return updateUserMission.startMission(userMission);
    }

    public List<UserMissionProgress> getMissionsInProgressByUserIdentifier(String userEmail, String platformName, ProgressStatusEnum status) {
        return findUserProgressMissionsByStatusAndPlatform.byUserEmail(userEmail, platformName, status);
    }

    public Optional<UserMissionGoalProgress> getNextGoal(String userEmail, String platformName, String missionId) {
        var userMission = findUserMissionByEmail.byMissionIdAndStatus(userEmail,platformName,  missionId, ProgressStatusEnum.IN_PROGRESS);
        return userMission.getNextGoal();
    }
    public Optional<UserMissionGoalProgress> getCurrentGoal(String userEmail, String platformName, String missionId){
        var userMission = findUserMissionByEmail.byMissionIdAndStatus(userEmail, platformName, missionId, ProgressStatusEnum.IN_PROGRESS);
        return userMission.getCurrentGoal();
    }

    public Optional<UserMissionGoalProgress> getLastGoal(String userEmail, String platformName, String missionId){
        var userMission = findUserMissionByEmail.byMissionIdAndStatus(userEmail,platformName, missionId, ProgressStatusEnum.IN_PROGRESS);
        return userMission.getLastGoal();
    }

    public UserMissionProgress resolveGoalInProgress(String userEmail, String platformName, String missionId){
        var userMission = findUserMissionByEmail
                .byMissionIdAndStatus(userEmail, platformName, missionId, ProgressStatusEnum.IN_PROGRESS);

        var currentGoal = userMission
                .getCurrentGoal()
                .orElseThrow(CurrentGoalNotFoundException::new);

        var nextGoal = userMission
                .getNextGoal();

        var updatedMission = updateUserMission.completeGoal(userMission, currentGoal);
        addGoalCompletedToUserProgress(userMission);

        nextGoal.ifPresentOrElse(
                next -> updateUserMission.startGoal(updatedMission, next),
                () -> {
                    updateUserMission.completeMission(updatedMission);
                    addPointsToUserProgress(updatedMission);
                    updateMissionEstimatedDuration.recalculate(userMission.getMission().getIdentifier());
                    userReward(updatedMission);
                }
        );

        return findUserMissionById.byId(userMission.getId());
    }

    private void addGoalCompletedToUserProgress(UserMissionProgress userMissionProgress) {
        var user = userMissionProgress.getUser();
        findUserProgress.findByUser(user).ifPresent(userProgress -> {
            userProgress.addGoalCompleted();
            updateUserProgress.updateUserProgress(userProgress);
        });
    }

    private UserProgress addPointsToUserProgress(UserMissionProgress userMissionProgress) {
        return userProgressPoints.addMissionPoints(userMissionProgress);
    }

    private void userReward(UserMissionProgress userMissionProgress){
        assignRewardToUser.assignReward(userMissionProgress.getId());
    }
}
