package io.github.thallesyan.gamification_api.infrastructure.web.clients;

import io.github.thallesyan.gamification_api.infrastructure.web.dto.response.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeycloakClient {

    private final RestTemplate restTemplate;

    @Value("${keycloak.url:http://127.0.0.1:8080}")
    private String keycloakUrl;

    @Value("${keycloak.realm:master}")
    private String realm;

    public TokenResponseDTO getToken(String clientId, String clientSecret) {
        String url = String.format("%s/realms/%s/protocol/openid-connect/token", keycloakUrl, realm);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(createMultiValueMap(clientId, clientSecret), getHeaders());

        try {
            log.info("Requesting token from Keycloak at: {}", url);
            ResponseEntity<TokenResponseDTO> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    TokenResponseDTO.class
            );

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                log.info("Token obtained successfully");
                return response.getBody();
            } else {
                log.error("Failed to obtain token. Status: {}", response.getStatusCode());
                throw new RuntimeException("Failed to obtain token from Keycloak");
            }
        } catch (RestClientException e) {
            log.error("Error calling Keycloak: {}", e.getMessage(), e);
            throw new RuntimeException("Error calling Keycloak: " + e.getMessage(), e);
        }
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

    private MultiValueMap<String, String> createMultiValueMap(String clientId, String clientSecret) {
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", clientId);
        body.add("client_secret", clientSecret);
        body.add("grant_type", "client_credentials");
        return body;
    }
}

