package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.domain.services.CreateUserProgress;
import io.github.thallesyan.gamification_api.domain.services.FindUserByEmail;
import io.github.thallesyan.gamification_api.domain.services.FindUserProgress;
import io.github.thallesyan.gamification_api.domain.services.RegisterUser;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserApplication {

    private final RegisterUser registerUser;
    private final FindUserByEmail findUserByEmail;
    private final CreateUserProgress createUserProgress;
    private final FindUserProgress findUserProgress;


    public UserApplication(RegisterUser registerUser, FindUserByEmail findUserByEmail, CreateUserProgress createUserProgress, FindUserProgress findUserProgress) {
        this.registerUser = registerUser;
        this.findUserByEmail = findUserByEmail;
        this.createUserProgress = createUserProgress;
        this.findUserProgress = findUserProgress;
    }

    public User registerUser(User user) {
        User registeredUser = registerUser.registerUser(user);
        UserProgress userProgress = new UserProgress(registeredUser);
        createUserProgress.createUserProgress(userProgress);
        
        return registeredUser;
    }

    public User findUserByEmail(String email, String platform) {
        return findUserByEmail.byEmail(email, platform)
                .orElseThrow(UserNotFoundException::new);
    }

    public Optional<UserProgress> findUserProgressByEmail(String email, String platform) {
        User user = findUserByEmail(email, platform);
        return findUserProgress.findByUser(user);
    }
}
