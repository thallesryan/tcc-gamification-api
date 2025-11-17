package io.github.thallesyan.gamification_api.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompletionTimeDTO {
    private Long days;
    private Long hours;
    private Long minutes;
    private Long seconds;
}

