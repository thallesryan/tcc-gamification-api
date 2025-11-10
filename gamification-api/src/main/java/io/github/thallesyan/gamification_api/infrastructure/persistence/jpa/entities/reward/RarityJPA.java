package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.reward;

import io.github.thallesyan.gamification_api.domain.entities.reward.RarityEnum;
import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.PlatformJPA;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rarity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RarityId.class)
public class RarityJPA {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "nivel", nullable = false, length = 40)
    private RarityEnum nivel;

    @Id
    @ManyToOne
    @JoinColumn(name = "platform", nullable = false)
    private PlatformJPA platform;

    @Column(name = "points", nullable = false)
    private Integer points;
}

