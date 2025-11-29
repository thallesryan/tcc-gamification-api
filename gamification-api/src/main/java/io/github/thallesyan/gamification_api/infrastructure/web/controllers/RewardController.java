package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.usecases.BadgeApplication;
import io.github.thallesyan.gamification_api.application.usecases.RarityApplication;
import io.github.thallesyan.gamification_api.application.usecases.RewardApplication;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.infrastructure.security.PlatformValidationService;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.RewardCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.RewardResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.UserRewardResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.RewardMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reward")
@AllArgsConstructor
@Tag(name = "Reward", description = "Endpoints para gerenciamento de recompensas")
public class RewardController {

    private final RewardApplication rewardApplication;
    private final RewardMapper rewardMapper;
    private final BadgeApplication badgeApplication;
    private final RarityApplication rarityApplication;
    private final PlatformValidationService platformValidationService;

    @Operation(summary = "Criar recompensa", description = "Cria uma nova recompensa no sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recompensa criada com sucesso",
                    content = @Content(schema = @Schema(implementation = RewardResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("/create")
    public ResponseEntity<RewardResponseDTO> createReward(
            @RequestBody @Valid RewardCreationRequestDTO rewardCreationRequestDTO,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform) {

        platformValidationService.validatePlatformAccess(platform);

        var reward = rewardMapper.toReward(rewardCreationRequestDTO, platform);


        var createdReward = rewardApplication.createReward(reward);
        return new ResponseEntity<>(rewardMapper.toRewardResponseDTO(createdReward), HttpStatus.CREATED);
    }

    @Operation(summary = "Listar todas as recompensas", description = "Retorna todas as recompensas do sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recompensas encontradas"),
            @ApiResponse(responseCode = "204", description = "Nenhuma recompensa encontrada")
    })
    @GetMapping()
    public ResponseEntity<List<RewardResponseDTO>> getAllRewards() {
        var rewards = rewardApplication.findAllRewards().stream()
                .map(rewardMapper::toRewardResponseDTO)
                .collect(Collectors.toList());
        
        if (rewards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(rewards, HttpStatus.OK);
    }

    @Operation(summary = "Buscar recompensa por ID", description = "Retorna os dados de uma recompensa pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recompensa encontrada",
                    content = @Content(schema = @Schema(implementation = RewardResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Recompensa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RewardResponseDTO> getRewardById(
            @Parameter(description = "ID da recompensa", required = true)
            @PathVariable("id") UUID id) {
        return rewardApplication.findRewardById(id)
                .map(reward -> new ResponseEntity<>(rewardMapper.toRewardResponseDTO(reward), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Buscar recompensas do usuário por email", description = "Retorna todas as recompensas de um usuário pelo email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recompensas encontradas",
                    content = @Content(schema = @Schema(implementation = UserRewardResponseDTO.class))),
            @ApiResponse(responseCode = "204", description = "Nenhuma recompensa encontrada"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/user/{email}")
    public ResponseEntity<List<UserRewardResponseDTO>> getUserRewardsByEmail(
            @Parameter(description = "Email do usuário", required = true)
            @PathVariable("email") String email,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform) {

        platformValidationService.validatePlatformAccess(platform);

        try {
            var userRewards = rewardApplication.findUserRewardsByEmail(email, platform);
            
            if (userRewards.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            var userRewardsDTO = userRewards.stream()
                    .map(rewardMapper::toUserRewardResponseDTO)
                    .collect(Collectors.toList());
            
            return new ResponseEntity<>(userRewardsDTO, HttpStatus.OK);
        } catch (io.github.thallesyan.gamification_api.application.exceptions.UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

