package io.github.thallesyan.gamification_api.infrastructure.web.erros;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BeanValidationError {
    private String field;
    private String message;
}
