package io.github.thallesyan.gamification_api.application.usecases;

import io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.services.FindUserByEmail;
import io.github.thallesyan.gamification_api.domain.services.RegisterUser;
import org.springframework.stereotype.Component;

@Component
public class UserApplication {

    private final RegisterUser registerUser;
    private final FindUserByEmail findUserByEmail;


    public UserApplication(RegisterUser registerUser, FindUserByEmail findUserByEmail) {
        this.registerUser = registerUser;
        this.findUserByEmail = findUserByEmail;
    }

    public User registerUser(User user) {
        return registerUser.registerUser(user);
    }

    public User findUserByEmail(String email) {
        return findUserByEmail.byEmail(email)
                .orElseThrow(UserNotFoundException::new);
    }
}
