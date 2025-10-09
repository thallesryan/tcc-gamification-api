package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindUserBoundary;
import io.github.thallesyan.gamification_api.domain.services.VerifyUserExists;
import org.springframework.stereotype.Service;

@Service
public class VerifyUserExistsImpl implements VerifyUserExists {

    private final FindUserBoundary findMissionBoundary;

    public VerifyUserExistsImpl(FindUserBoundary findMissionBoundary) {
        this.findMissionBoundary = findMissionBoundary;
    }

    @Override
    public boolean byEmail(String email) {
        return findMissionBoundary.verifyUserExistsByEmail(email);
    }
}
