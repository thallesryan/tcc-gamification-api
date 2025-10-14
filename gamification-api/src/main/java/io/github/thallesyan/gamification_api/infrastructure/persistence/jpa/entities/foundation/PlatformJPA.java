package io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.foundation;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "platform", uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlatformJPA extends BaseEntityJPA{
    private String name;
}
