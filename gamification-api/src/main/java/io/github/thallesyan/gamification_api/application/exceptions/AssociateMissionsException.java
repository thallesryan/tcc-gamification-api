package io.github.thallesyan.gamification_api.application.exceptions;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.search.MissionsSearch;
import io.github.thallesyan.gamification_api.infrastructure.exceptions.BaseException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class AssociateMissionsException extends BaseException {
    private final MissionsSearch missionsSearch;
    private final List<Mission> missionsAlreadyExisted;

    public AssociateMissionsException(MissionsSearch missionsSearch, List<Mission> missionsAlreadyExisted) {
        super("Some Missions cound not be associated to user", "MISSION_ASSOCIATION_FAILED", HttpStatus.PARTIAL_CONTENT);
        this.missionsSearch = missionsSearch;
        this.missionsAlreadyExisted = missionsAlreadyExisted;
    }
}
