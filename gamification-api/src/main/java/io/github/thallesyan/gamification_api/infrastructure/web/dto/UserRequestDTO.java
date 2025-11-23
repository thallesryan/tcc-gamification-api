package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO para criação de usuário")
public class UserRequestDTO {
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    @Schema(description = "Nome completo do usuário", example = "João Silva", required = true)
    private String name;
    
    @NotNull(message = "Email cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must have a valid format")
    @Size(max = 255, message = "Email must have at most 255 characters")
    @Schema(description = "Email do usuário", example = "joao.silva@example.com", required = true)
    private String email;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Data de nascimento no formato dd/MM/yyyy", example = "08/04/2002", type = "string", format = "dd/MM/yyyy")
    private LocalDate dateOfBirth;
    
    @Valid
    @Schema(description = "Informações da plataforma", required = true)
    private PlatformRequestDTO platform;
}
