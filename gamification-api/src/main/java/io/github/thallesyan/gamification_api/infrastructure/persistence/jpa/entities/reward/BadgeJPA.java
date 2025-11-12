package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.reward;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation.BaseEntityJPA;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "badges")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BadgeJPA extends BaseEntityJPA {

    public BadgeJPA(UUID identifier) {
        super(identifier);
    }

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({
        @JoinColumn(name = "rarity_nivel", referencedColumnName = "nivel"),
        @JoinColumn(name = "platform", referencedColumnName = "platform")
    })
    private RarityJPA rarity;

    public static BadgeJPA byIdentifier(String identifier) {
        return new BadgeJPA(UUID.fromString(identifier));
    }

    public static BadgeJPA byIdentifier(UUID identifier) {
        return new BadgeJPA(identifier);
    }
}

