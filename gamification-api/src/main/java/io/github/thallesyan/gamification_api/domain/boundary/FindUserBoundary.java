package io.github.thallesyan.gamification_api.domain.boundary;

import io.github.thallesyan.gamification_api.domain.entities.foundation.User;

import java.util.Optional;

public interface FindUserBoundary {

    Optional<User> ByEmail(String email);

    boolean verifyUserExistsByEmail(String email);
}
