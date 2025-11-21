package io.github.thallesyan.gamification_api.infrastructure.web.controllers;

import io.github.thallesyan.gamification_api.infrastructure.web.clients.KeycloakClient;
import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.TokenResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Endpoints de autenticação e obtenção de tokens")
public class AuthController {

    private final KeycloakClient keycloakClient;

    @Operation(
            summary = "Obter token de autenticação",
            description = "Obtém um token de acesso do Keycloak usando client credentials"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token obtido com sucesso",
                    content = @Content(schema = @Schema(implementation = TokenResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PostMapping(value = "/token", consumes = "application/x-www-form-urlencoded")
    public ResponseEntity<TokenResponseDTO> getToken(
            @Parameter(description = "ID do cliente", required = true)
            @RequestParam(value = "client_id", required = true) String clientId,
            @Parameter(description = "Secret do cliente", required = true)
            @RequestParam(value = "client_secret", required = true) String clientSecret) {
        try {
            TokenResponseDTO tokenResponse = keycloakClient.getToken(clientId, clientSecret);
            return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

