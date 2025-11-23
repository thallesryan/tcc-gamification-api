package io.github.thallesyan.gamification_api.infrastructure.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(description = "DTO para informações da plataforma")
public class PlatformRequestDTO {
    @Schema(description = "Nome da plataforma", example = "PUC_MINAS", required = true)
    private String name;
    
    @Schema(description = "Pontos base para progresso (opcional)", example = "100")
    private Integer progressBasePoints;
    
    @Schema(description = "Fórmula de progresso (opcional)", example = "1.5")
    private Double progressFormula;
}
