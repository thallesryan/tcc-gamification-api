package io.github.thallesyan.gamification_api.infrastructure.web.mappers;

import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.entities.progress.UserReward;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.RewardCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.RewardResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.UserRewardResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {BadgeMapper.class})
public interface RewardMapper {

    @Mapping(target = "identifier", source = "rewardCreationRequestDTO.identifier")
    @Mapping(target = "name", source = "rewardCreationRequestDTO.name")
    @Mapping(target = "description", source = "rewardCreationRequestDTO.description")
    @Mapping(target = "points", source = "rewardCreationRequestDTO.points")
//    @Mapping(target = "badge.rarity.platform.name", source = "platform")
//    @Mapping(target = "badge.rarity.value", source = "rewardCreationRequestDTO.badge.rarity")
//    @Mapping(target = "badge.name", source = "rewardCreationRequestDTO.badge.name")
//    @Mapping(target = "badge.description", source = "rewardCreationRequestDTO.badge.description")
    @Mapping(target = "badge.identifier", source = "rewardCreationRequestDTO.badge.identifier")
    Reward toReward(RewardCreationRequestDTO rewardCreationRequestDTO, String platform);

    @Mapping(target = "identifier", source = "rewardCreationRequestDTO.identifier")
    @Mapping(target = "name", source = "rewardCreationRequestDTO.name")
    @Mapping(target = "description", source = "rewardCreationRequestDTO.description")
    @Mapping(target = "points", source = "rewardCreationRequestDTO.points")
    @Mapping(target = "badge.identifier", source = "rewardCreationRequestDTO.badge.identifier")
    Reward toReward(RewardCreationRequestDTO rewardCreationRequestDTO);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "points", source = "points")
    @Mapping(target = "badge", source = "badge")
    RewardResponseDTO toRewardResponseDTO(Reward reward);

    @Mapping(target = "identifier", source = "identifier")
    @Mapping(target = "reward", source = "reward")
    @Mapping(target = "status", source = "status", qualifiedByName = "statusToString")
    @Mapping(target = "earnedAt", source = "earnedAt")
    @Mapping(target = "pointsValue", source = "pointsValue")
    UserRewardResponseDTO toUserRewardResponseDTO(UserReward userReward);

    @org.mapstruct.Named("statusToString")
    default String statusToString(UserReward.UserRewardStatus status) {
        return status != null ? status.name() : null;
    }
}

