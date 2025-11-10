package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.infrastructure.web.dto.RarityPointsRequestDTO;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.RarityResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/badge/")
@AllArgsConstructor
public class BadgeController {

    //todo: implement
    @PostMapping("create")
    public ResponseEntity createBadge(@RequestHeader("platform") String platform) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //todo: implement
    @GetMapping()
    public ResponseEntity getByRarity(@RequestParam("rarity") String rarityStatus, @RequestHeader("platform") String platform) {

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
