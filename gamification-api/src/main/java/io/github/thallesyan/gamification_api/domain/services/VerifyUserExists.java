package io.github.thallesyan.gamification_api.domain.services;

public interface VerifyUserExists {

    boolean byEmail(String email);
}
