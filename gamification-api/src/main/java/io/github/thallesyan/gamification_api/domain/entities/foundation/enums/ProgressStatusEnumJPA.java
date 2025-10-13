package io.github.thallesyan.gamification_api.domain.entities.foundation.enums;

import io.github.thallesyan.gamification_api.infrastructure.persistence.jpa.entities.progress.ProgressStatusEnum;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProgressStatusEnumJPA {
    ASSIGNED(ProgressStatusEnum.ASSIGNED),
    IN_PROGRESS(ProgressStatusEnum.IN_PROGRESS),
    COMPLETED(ProgressStatusEnum.COMPLETED),
    FAILED(ProgressStatusEnum.FAILED),
    CANCELLED(ProgressStatusEnum.CANCELLED),;

    private final ProgressStatusEnum progressStatusEnum;

    ProgressStatusEnumJPA(ProgressStatusEnum progressStatusEnum) {
        this.progressStatusEnum = progressStatusEnum;
    }

    public static ProgressStatusEnumJPA getProgressJPAByProgressStatusEnum(ProgressStatusEnum progressStatusEnum){
        return Arrays.stream(ProgressStatusEnumJPA.values())
                .filter(pJpa -> pJpa.progressStatusEnum == progressStatusEnum)
                .findFirst()
                .get();
    }
}
