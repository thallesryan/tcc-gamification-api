package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.dtos.CompletionTimeDTO;
import io.github.thallesyan.gamification_api.application.usecases.RankingApplication;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.infrastructure.security.PlatformValidationService;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.enums.RankingByEnum;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionRankingEntryResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.RankingEntryResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.RankingMapper;
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

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/ranking")
@AllArgsConstructor
@Tag(name = "Ranking", description = "Endpoints para consulta de rankings")
public class RankingController {

    private final RankingApplication rankingApplication;
    private final PlatformValidationService platformValidationService;

    @Operation(summary = "Obter ranking geral", description = "Retorna o ranking geral de usuários por nível, goals ou missões completadas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ranking obtido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    })
    @GetMapping()
    public ResponseEntity<List<RankingEntryResponseDTO>> getRanking(
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform,
            @Parameter(description = "Critério de ranking (LEVEL, GOALS_COMPLETED, MISSION_COMPLETED)", required = true)
            @RequestHeader("rankingBy") String rankingBy) {
        
        platformValidationService.validatePlatformAccess(platform);
        
        RankingByEnum rankingByEnum = RankingByEnum.fromString(rankingBy);
        List<UserProgress> userProgressList = rankingApplication.getRankingByPlatform(
                platform, 
                RankingMapper.toRankingType(rankingByEnum)
        );
        
        List<RankingEntryResponseDTO> ranking = buildRankingResponse(userProgressList, rankingByEnum);
        
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }

    @Operation(summary = "Obter ranking de missão", description = "Retorna o ranking de uma missão específica ordenado por tempo de conclusão")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ranking da missão obtido com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos")
    })
    @GetMapping("/mission/{missionId}")
    public ResponseEntity<List<MissionRankingEntryResponseDTO>> getMissionRanking(
            @Parameter(description = "ID da missão", required = true)
            @PathVariable UUID missionId,
            @Parameter(description = "Nome da plataforma", required = true)
            @RequestHeader("platform") String platform) {
        
        platformValidationService.validatePlatformAccess(platform);
        
        List<UserMissionProgress> missionProgressList = rankingApplication
                .getMissionRankingByMissionIdAndPlatform(missionId, platform);

        List<MissionRankingEntryResponseDTO> ranking = buildMissionRankingResponse(missionProgressList);
        
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }

    private List<RankingEntryResponseDTO> buildRankingResponse(List<UserProgress> sortedList, RankingByEnum rankingBy) {
        return IntStream.range(0, sortedList.size())
                .mapToObj(index -> {
                    UserProgress up = sortedList.get(index);
                    Integer value = getValueByRankingCriteria(up, rankingBy);
                    return new RankingEntryResponseDTO(
                            index + 1,
                            up.getUser().getIdentifier(),
                            up.getUser().getName(),
                            up.getUser().getEmail(),
                            value
                    );
                })
                .collect(Collectors.toList());
    }

    private Integer getValueByRankingCriteria(UserProgress up, RankingByEnum rankingBy) {
        return switch (rankingBy) {
            case LEVEL -> up.getCurrentLevel();
            case GOALS_COMPLETED -> up.getGoalsCompleted();
            case MISSION_COMPLETED -> up.getMissionsCompleted();
        };
    }

    private List<MissionRankingEntryResponseDTO> buildMissionRankingResponse(List<UserMissionProgress> missionProgressList) {
        return IntStream.range(0, missionProgressList.size())
                .mapToObj(index -> {
                    UserMissionProgress ump = missionProgressList.get(index);
                    LocalDateTime startedAt = ump.getStartDate();
                    LocalDateTime completedAt = ump.getCompletionDate();

                    return new MissionRankingEntryResponseDTO(
                            index + 1,
                            ump.getUser().getIdentifier(),
                            ump.getUser().getName(),
                            ump.getUser().getEmail(),
                            startedAt,
                            completedAt,
                            calculateCompletionTime(ump)
                    );
                })
                .collect(Collectors.toList());
    }

    public String calculateCompletionTime(UserMissionProgress userMissionProgress) {
        if (userMissionProgress == null ||
                userMissionProgress.getStartDate() == null ||
                userMissionProgress.getCompletionDate() == null) {
            return null;
        }

        Duration duration = Duration.between(
                userMissionProgress.getStartDate(),
                userMissionProgress.getCompletionDate()
        );

        long days = duration.toDays();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format("Days: %d, hours: %d, minutes: %d, seconds: %d", days, hours, minutes, seconds);
    }
}

