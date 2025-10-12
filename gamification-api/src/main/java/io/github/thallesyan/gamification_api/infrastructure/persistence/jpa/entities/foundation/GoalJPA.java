package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "goals")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GoalJPA extends BaseInformationJPA {

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private MissionJPA mission;

    @Column(name = "order_number", length = 255)
    private Integer order;

    public GoalJPA(UUID identifier) {
        super(identifier);
    }
}
