package io.github.thallesyan.gamification_api.infrastructure.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "io.github.thallesyan.gamification_api.infrastructure.persistence.jpa")
@EntityScan(basePackages = "io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities")
@EnableTransactionManagement
public class PersistenceConfig {
}
