package io.github.thallesyan.gamification_api.domain.services.impl;

import io.github.thallesyan.gamification_api.domain.boundary.FindUserByEmailBoundary;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.services.FindUserByEmail;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindUserByEmailImpl implements FindUserByEmail {

    private final FindUserByEmailBoundary findUserByEmailBoundary;

    public FindUserByEmailImpl(FindUserByEmailBoundary findUserByEmailBoundary) {
        this.findUserByEmailBoundary = findUserByEmailBoundary;
    }

    @Override
    public Optional<User> byEmail(String email) {
        return findUserByEmailBoundary.findUserByEmail(email);
    }
}
