package io.github.thallesyan.gamification_api.infrastructure.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO de entrada no ranking geral")
public class RankingEntryResponseDTO {
    @Schema(description = "Posição no ranking (1 = primeiro lugar)", example = "1")
    private Integer position;
    
    @Schema(description = "Identificador único do usuário", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID userId;
    
    @Schema(description = "Nome do usuário", example = "João Silva")
    private String userName;
    
    @Schema(description = "Email do usuário", example = "joao.silva@example.com")
    private String userEmail;
    
    @Schema(description = "Valor usado para ordenação (nível, goals completadas ou missões completadas dependendo do critério)", example = "25")
    private Integer value;
}

