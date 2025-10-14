package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException;
import io.github.thallesyan.gamification_api.application.usecases.UserApplication;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.UserMapper;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.domain.services.FindUserByEmail;
import io.github.thallesyan.gamification_api.domain.services.RegisterUser;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.UserRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.UserResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user/")
@AllArgsConstructor
public class UserController {

    private final UserApplication userApplication;
    private final UserMapper userMapper;

    @PostMapping("register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        User registeredUser = userApplication.registerUser(userMapper.toUser(userRequestDTO));
        return new ResponseEntity<>(userMapper.toUserResponseDTO(registeredUser), HttpStatus.CREATED);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(@PathVariable String email) {
        try {
            var userResponseDTO = userMapper.toUserResponseDTO(userApplication.findUserByEmail(email));
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        }catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}
