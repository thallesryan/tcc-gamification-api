package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotNull
    @NotNull
    private String name;
    @Email
    private String email;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;
    private PlatformRequestDTO platform;
}
