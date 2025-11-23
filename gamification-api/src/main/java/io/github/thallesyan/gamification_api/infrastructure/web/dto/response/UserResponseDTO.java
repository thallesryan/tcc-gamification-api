package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de resposta com dados do usuário")
public class UserResponseDTO {
    @Schema(description = "Identificador único do usuário", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID identifier;
    
    @Schema(description = "Nome completo do usuário", example = "João Silva")
    private String name;
    
    @Schema(description = "Email do usuário", example = "joao.silva@example.com")
    private String email;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Schema(description = "Data de nascimento no formato dd/MM/yyyy", example = "08/04/2002", type = "string", format = "dd/MM/yyyy")
    private Date dateOfBirth;
    
    @Schema(description = "Nome da plataforma", example = "PUC_MINAS")
    private String platform;
}
