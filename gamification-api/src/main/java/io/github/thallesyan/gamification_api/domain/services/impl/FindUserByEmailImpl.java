package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindUserBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.services.FindUserByEmail;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserByEmailImpl implements FindUserByEmail {

    private final FindUserBoundary findUserBoundary;

    public FindUserByEmailImpl(FindUserBoundary findUserBoundary) {
        this.findUserBoundary = findUserBoundary;
    }

    @Override
    public Optional<User> byEmail(String email, String platform) {
        return findUserBoundary.ByEmail(email, platform);
    }
}
