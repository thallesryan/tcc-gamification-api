package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.progress.enums.RankingType;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.enums.RankingByEnum;

public class RankingMapper {
    
    public static RankingType toRankingType(RankingByEnum rankingByEnum) {
        return switch (rankingByEnum) {
            case POINTS -> RankingType.POINTS;
            case GOALS_COMPLETED -> RankingType.GOALS_COMPLETED;
            case MISSION_COMPLETED -> RankingType.MISSION_COMPLETED;
        };
    }
}

