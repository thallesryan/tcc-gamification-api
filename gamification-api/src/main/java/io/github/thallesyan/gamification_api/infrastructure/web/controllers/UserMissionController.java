package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.exceptions.UserMissionNotFound;
import io.github.thallesyan.gamification_api.application.usecases.UserMissionApplication;
import io.github.thallesyan.gamification_api.infrastructure.exceptions.InvalidUuidException;
import io.github.thallesyan.gamification_api.infrastructure.security.PlatformValidationService;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.BindUserMissionRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionBinding;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.ResolveGoalRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.StartMissionRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionProgressResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionStartResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.NextGoalResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.GoalMapper;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.UserMissionGoalMapper;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.UserMissionMapper;
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
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-mission/")
@AllArgsConstructor
@Tag(name = "User Mission", description = "Endpoints para gerenciamento de missões de usuários")
public class UserMissionController {

    private final UserMissionApplication userMissionApplication;
    private final UserMissionMapper userMissionMapper;
    private final UserMissionGoalMapper userMissionGoalMapper;
    private final PlatformValidationService platformValidationService;

    @Operation(summary = "Vincular missões ao usuário", description = "Associa uma ou mais missões a um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missões vinculadas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("bind-missions")
    public ResponseEntity<MissionResponseDTO> bindMissionsToUser(@RequestBody @Valid BindUserMissionRequestDTO bindUserMissionRequestDTO) {
        platformValidationService.validatePlatformAccess(bindUserMissionRequestDTO.getPlatform());
        
        var missions = bindUserMissionRequestDTO.getMissions();

        var missionsIds = missions.stream().map(MissionBinding::getIdentifier).map(this::stringToUUID).collect(Collectors.toList());
        userMissionApplication.associateUserMission(bindUserMissionRequestDTO.getUserEmail(), bindUserMissionRequestDTO.getPlatform(), missionsIds);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //TODO validate trying to start mission already started
    @Operation(summary = "Iniciar missão", description = "Inicia uma missão para um usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão iniciada com sucesso",
                    content = @Content(schema = @Schema(implementation = MissionStartResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("start-mission")
    public ResponseEntity<MissionStartResponseDTO> start(
            @RequestBody @Valid StartMissionRequestDTO startMissionRequestDTO,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform) {
        
        platformValidationService.validatePlatformAccess(platform);
        
        var userMission = switch (startMissionRequestDTO.getUserIdentification().getUserIdentifierType()){
            case EMAIL -> userMissionApplication.startMissionByUserEmail(startMissionRequestDTO.getUserIdentification().getUserIdentifierValue(),platform, startMissionRequestDTO.getMissionIdentifier());
            case IDENTIFIER -> userMissionApplication.startMissionByUserIdentifier(startMissionRequestDTO.getUserIdentification().getUserIdentifierValue(),platform, startMissionRequestDTO.getMissionIdentifier());
        };

        return new ResponseEntity<>(userMissionMapper.toStartMissionResponseDTO(userMission), HttpStatus.CREATED);
    }

    //todo resolve goal, modify return if all goals are finished
    //todo: return actual goal and next goal
    @Operation(summary = "Obter próximo goal", description = "Retorna o goal atual, próximo e último de uma missão do usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Goals obtidos com sucesso",
                    content = @Content(schema = @Schema(implementation = NextGoalResponseDTO.class))),
            @ApiResponse(responseCode = "422", description = "Missão do usuário não encontrada")
    })
    @GetMapping("mission/{missionId}/user/{userEmail}/next-goal")
    public ResponseEntity<NextGoalResponseDTO> getGoal(
            @Parameter(description = "ID da missão", required = true)
            @PathVariable("missionId") String missionId,
            @Parameter(description = "Email do usuário", required = true)
            @PathVariable("userEmail") String userEmail,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform
    ) {
        platformValidationService.validatePlatformAccess(platform);
        
        try{
            var currentGoal = userMissionApplication
                    .getCurrentGoal(userEmail, platform, missionId)
                    .orElse(null);

            var nextGoal = userMissionApplication
                    .getNextGoal(userEmail, platform, missionId)
                    .orElse(null);;

            var lastGoal = userMissionApplication
                    .getLastGoal(userEmail, platform, missionId)
                    .orElse(null);;

            var nextGoalResponse = new NextGoalResponseDTO(userMissionGoalMapper.userGoalToUserGoalProgress(currentGoal), userMissionGoalMapper.userGoalToUserGoalProgress(nextGoal), userMissionGoalMapper.userGoalToUserGoalProgress(lastGoal));
            return ResponseEntity.ok(nextGoalResponse);
        }catch (UserMissionNotFound ex){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Operation(summary = "Resolver próximo goal", description = "Marca o goal atual como concluído e avança para o próximo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Goal resolvido com sucesso",
                    content = @Content(schema = @Schema(implementation = MissionStartResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping("mission/{missionId}/user/{userEmail}/resolve-next-goal")
    public ResponseEntity<MissionStartResponseDTO> resolveGoal(
            @Parameter(description = "ID da missão", required = true)
            @PathVariable("missionId") String missionId,
            @Parameter(description = "Email do usuário", required = true)
            @PathVariable("userEmail") String userEmail,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform
    ) {

        platformValidationService.validatePlatformAccess(platform);

        var userMission = userMissionApplication.resolveGoalInProgress(userEmail, platform, missionId);
        return new ResponseEntity<>(userMissionMapper.toStartMissionResponseDTO(userMission), HttpStatus.OK);

    }

    @Operation(summary = "Listar missões por status", description = "Retorna todas as missões de um usuário com um determinado status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missões encontradas"),
            @ApiResponse(responseCode = "204", description = "Nenhuma missão encontrada")
    })
    @GetMapping("{userEmail}/status/{missionStatus}")
    public ResponseEntity<List<MissionProgressResponseDTO>> bindMissionsToUser(
            @Parameter(description = "Email do usuário", required = true)
            @PathVariable("userEmail") String userEmail,
            @Parameter(description = "Status da missão (IN_PROGRESS, COMPLETED, NOT_STARTED)", required = true)
            @PathVariable("missionStatus") String missionStatus,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform
    ) {
        platformValidationService.validatePlatformAccess(platform);
        
        var missions = userMissionApplication.getMissionsInProgressByUserIdentifier(userEmail, platform, ProgressStatusEnum.fromString(missionStatus));
        if(missions.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        var missionProgressResponse = missions.stream().map(userMissionMapper::toMissionProgressResponseDTO).toList();
        return new ResponseEntity<>(missionProgressResponse, HttpStatus.OK);
    }

    private UUID stringToUUID(String uuid){
        try{
            return UUID.fromString(uuid);
        }catch (IllegalArgumentException e){
            throw new InvalidUuidException(uuid);
        }

    }
}
