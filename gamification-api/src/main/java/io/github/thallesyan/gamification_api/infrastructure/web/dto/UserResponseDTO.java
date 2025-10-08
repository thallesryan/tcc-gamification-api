package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private UUID identifier;
    private String name;
    private String email;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;
}
