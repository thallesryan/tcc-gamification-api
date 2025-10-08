package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.enums.BaseStatusEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BaseInformationJPA extends BaseEntityJPA {
    
    @Column(name = "title", nullable = false, length = 255)
    private String title;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BaseStatusEnum status = BaseStatusEnum.ACTIVE;
}
