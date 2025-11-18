package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindUserMissionBoundary;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.services.FindUserMissionById;
import io.github.thallesyan.gamification_api.infrastructure.exceptions.UserMissionIdNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindUserMissionByIdImpl implements FindUserMissionById {

    private final FindUserMissionBoundary findUserMissionBoundary;

    public FindUserMissionByIdImpl(FindUserMissionBoundary findUserMissionBoundary) {
        this.findUserMissionBoundary = findUserMissionBoundary;
    }

    @Override
    public UserMissionProgress byId(Integer id) {
        return findUserMissionBoundary
                .byId(id)
                .orElseThrow(() -> new UserMissionIdNotFoundException(id));
    }
}
