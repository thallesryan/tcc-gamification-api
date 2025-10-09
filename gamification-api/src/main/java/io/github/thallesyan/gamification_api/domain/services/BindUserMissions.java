package io.github.thallesyan.gamification_api.domain.services;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Mission;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;

import java.util.List;

public interface BindUserMissions {
    void bindMissions(User userEmail, List<Mission> missions);
}
