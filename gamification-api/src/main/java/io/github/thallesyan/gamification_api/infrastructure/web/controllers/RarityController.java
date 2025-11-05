package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.infrastructure.web.dto.BindUserMissionRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.MissionBinding;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.RarityPointsRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.MissionResponseDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.RarityResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/rarity/")
@AllArgsConstructor
public class RarityController {

    //todo: implement
    @GetMapping("all")
    public ResponseEntity<List<RarityResponseDTO>> getAllRarities(@RequestHeader("platform") String platform) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //todo: implement
    @GetMapping("points")
    public ResponseEntity<List<RarityResponseDTO>> associatePoints(@RequestBody RarityPointsRequestDTO rarityPointsRequestDTO, @RequestHeader("platform") String platform) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}