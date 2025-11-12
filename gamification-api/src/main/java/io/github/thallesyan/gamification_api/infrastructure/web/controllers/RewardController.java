package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.application.usecases.BadgeApplication;
import io.github.thallesyan.gamification_api.application.usecases.RarityApplication;
import io.github.thallesyan.gamification_api.application.usecases.RewardApplication;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Platform;
import io.github.thallesyan.gamification_api.domain.entities.foundation.Reward;
import io.github.thallesyan.gamification_api.domain.entities.reward.Badge;
import io.github.thallesyan.gamification_api.domain.entities.reward.Rarity;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.RewardCreationRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.RewardResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.mappers.RewardMapper;
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
public class RewardController {

    private final RewardApplication rewardApplication;
    private final RewardMapper rewardMapper;
    private final BadgeApplication badgeApplication;
    private final RarityApplication rarityApplication;

    @PostMapping("/create")
    public ResponseEntity<RewardResponseDTO> createReward(
            @RequestBody @Valid RewardCreationRequestDTO rewardCreationRequestDTO,
            @RequestHeader("platform") String platform) {

        Reward reward = rewardMapper.toReward(rewardCreationRequestDTO, platform);

        var createdReward = rewardApplication.createReward(reward);
        return new ResponseEntity<>(rewardMapper.toRewardResponseDTO(createdReward), HttpStatus.CREATED);
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<RewardResponseDTO> getRewardById(@PathVariable("id") UUID id) {
        return rewardApplication.findRewardById(id)
                .map(reward -> new ResponseEntity<>(rewardMapper.toRewardResponseDTO(reward), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

