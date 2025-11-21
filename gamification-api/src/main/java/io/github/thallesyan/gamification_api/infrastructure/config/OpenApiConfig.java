package io.github.thallesyan.gamification_api.infrastructure.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gamification API")
                        .version("1.0.0")
                        .description("API para sistema de gamificação com missões, badges, recompensas e rankings")
                        .contact(new Contact()
                                .name("Thalles Ryan")
                                .url("https://github.com/thallesyan"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8081")
                                .description("Servidor de Desenvolvimento"),
                        new Server()
                                .url("http://127.0.0.1:8081")
                                .description("Servidor Local")
                ));
    }
}

