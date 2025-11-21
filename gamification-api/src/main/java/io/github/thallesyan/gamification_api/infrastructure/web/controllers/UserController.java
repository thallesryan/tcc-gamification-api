package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException;
import io.github.thallesyan.gamification_api.application.usecases.PlatformApplication;
import io.github.thallesyan.gamification_api.application.usecases.UserApplication;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.LevelPointsResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.UserMapper;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.UserProgressMapper;
import io.github.thallesyan.gamification_api.domain.entities.foundation.User;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.UserRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.UserResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.UserProgressResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
@AllArgsConstructor
@Tag(name = "User", description = "Endpoints para gerenciamento de usuários")
public class UserController {

    private final UserApplication userApplication;
    private final UserMapper userMapper;
    private final UserProgressMapper userProgressMapper;
    private final PlatformApplication platformApplication;

    @Operation(summary = "Registrar novo usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("register")
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody UserRequestDTO userRequestDTO) {
        User registeredUser = userApplication.registerUser(userMapper.toUser(userRequestDTO));
        return new ResponseEntity<>(userMapper.toUserResponseDTO(registeredUser), HttpStatus.CREATED);
    }

    @Operation(summary = "Buscar usuário por email", description = "Retorna os dados do usuário pelo email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(schema = @Schema(implementation = UserResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Usuário não encontrado")
    })
    @GetMapping("email/{email}")
    public ResponseEntity<UserResponseDTO> getUserByEmail(
            @Parameter(description = "Email do usuário", required = true)
            @PathVariable String email,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform) {

        try {
            var userResponseDTO = userMapper.toUserResponseDTO(userApplication.findUserByEmail(email, platform));
            return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
        }catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Buscar progresso do usuário", description = "Retorna o progresso do usuário pelo email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Progresso encontrado",
                    content = @Content(schema = @Schema(implementation = UserProgressResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Progresso não encontrado")
    })
    @GetMapping("email/{email}/progress")
    public ResponseEntity<UserProgressResponseDTO> getUserProgressByEmail(
            @Parameter(description = "Email do usuário", required = true)
            @PathVariable String email,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform) {
        try {
            var userProgressOpt = userApplication.findUserProgressByEmail(email, platform);
            if (userProgressOpt.isPresent()) {
                var userProgressResponseDTO = userProgressMapper.toUserProgressResponseDTO(userProgressOpt.get());
                return new ResponseEntity<>(userProgressResponseDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @Operation(summary = "Calcular pontos do nível", description = "Calcula os pontos necessários para um determinado nível")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pontos calculados com sucesso",
                    content = @Content(schema = @Schema(implementation = LevelPointsResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @GetMapping("progress/level/{levelNumber}/points")
    public ResponseEntity<LevelPointsResponseDTO> getLevelPoints(
            @Parameter(description = "Número do nível", required = true)
            @PathVariable Integer levelNumber,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform) {
        
        var platformEntity = platformApplication.findByName(platform);
        
        if (platformEntity.getProgressBasePoints() == null || platformEntity.getProgressFormula() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        Integer pointsRequired = platformEntity.calculateLevelPoints(levelNumber);
        
        var response = new LevelPointsResponseDTO(levelNumber, pointsRequired, platform);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
