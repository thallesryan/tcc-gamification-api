package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.mappers.UserMapper;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.services.FindUserByEmail;
import io.github.thallesyan.gamification_api.domain.services.RegisterUser;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.UserRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user/")
@AllArgsConstructor
public class UserController {

    private final RegisterUser registerUser;
    private final FindUserByEmail findUserByEmail;
    private final UserMapper userMapper;

    @PostMapping("register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        User registeredUser = registerUser.registerUser(userMapper.toUser(userRequestDTO));
        return new ResponseEntity<>(userMapper.toUserResponseDTO(registeredUser), HttpStatus.CREATED);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        Optional<User> user = findUserByEmail.byEmail(email);
        
        if (user.isPresent()) {
            UserResponseDTO response = userMapper.toUserResponseDTO(user.get());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
