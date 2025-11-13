package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.usecases.RankingApplication;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserMissionProgress;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserProgress;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.enums.RankingByEnum;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionRankingEntryResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.RankingEntryResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.RankingMapper;
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
public class RankingController {

    private final RankingApplication rankingApplication;

    @GetMapping()
    public ResponseEntity<List<RankingEntryResponseDTO>> getRanking(
            @RequestHeader("platform") String platform,
            @RequestHeader("rankingBy") String rankingBy) {
        
        RankingByEnum rankingByEnum = RankingByEnum.fromString(rankingBy);
        List<UserProgress> userProgressList = rankingApplication.getRankingByPlatform(
                platform, 
                RankingMapper.toRankingType(rankingByEnum)
        );
        
        List<RankingEntryResponseDTO> ranking = buildRankingResponse(userProgressList, rankingByEnum);
        
        return new ResponseEntity<>(ranking, HttpStatus.OK);
    }

    @GetMapping("/mission/{missionId}")
    public ResponseEntity<List<MissionRankingEntryResponseDTO>> getMissionRanking(
            @PathVariable UUID missionId,
            @RequestHeader("platform") String platform) {
        
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
            case POINTS -> up.getTotalPoints();
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
                    Long completionTimeInSeconds = null;
                    
                    if (startedAt != null && completedAt != null) {
                        completionTimeInSeconds = Duration.between(startedAt, completedAt).getSeconds();
                    }
                    
                    return new MissionRankingEntryResponseDTO(
                            index + 1,
                            ump.getUser().getIdentifier(),
                            ump.getUser().getName(),
                            ump.getUser().getEmail(),
                            startedAt,
                            completedAt,
                            completionTimeInSeconds
                    );
                })
                .collect(Collectors.toList());
    }
}

