package io.github.thallesyan.gamification_api.domain.entities.foundation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes para Platform.calculateLevelPoints")
class PlatformCalculateLevelPointsTest {

    private Platform platform;

    @BeforeEach
    void setUp() {
        platform = new Platform("test-platform", 1000, 0.75);
    }

    @Test
    @DisplayName("Deve retornar progressBasePoints quando level for 1")
    void deveRetornarProgressBasePointsQuandoLevelFor1() {
        // Given
        int level = 1;
        Integer expected = 1000;

        // When
        Integer result = platform.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve calcular corretamente para level 2")
    void deveCalcularCorretamenteParaLevel2() {
        // Given
        int level = 2;
        // Fórmula: 1000 * (1 + 0.75) = 1000 * 1.75 = 1750
        Integer expected = 1750;

        // When
        Integer result = platform.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve calcular corretamente para level 3")
    void deveCalcularCorretamenteParaLevel3() {
        // Given
        int level = 3;
        // Fórmula: 1000 * (1 + 0.75) ^ 2 = 1000 * 1.75 ^ 2 = 1000 * 3.0625 = 3062
        Integer expected = 3062;

        // When
        Integer result = platform.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve calcular corretamente para level 4")
    void deveCalcularCorretamenteParaLevel4() {
        // Given
        int level = 4;
        // Fórmula: 1000 * (1 + 0.75) ^ 3 = 1000 * 1.75 ^ 3 = 1000 * 5.359375 = 5359
        Integer expected = 5359;

        // When
        Integer result = platform.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve calcular corretamente para level 5")
    void deveCalcularCorretamenteParaLevel5() {
        // Given
        int level = 5;
        // Fórmula: 1000 * (1 + 0.75) ^ 4 = 1000 * 1.75 ^ 4 = 1000 * 9.37890625 = 9378
        Integer expected = 9378;

        // When
        Integer result = platform.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @CsvSource({
        "1, 1000",
        "2, 1750",
        "3, 3062",
        "4, 5359",
        "5, 9378"
    })
    @DisplayName("Deve calcular corretamente para múltiplos níveis com progressBasePoints=1000 e progressFormula=0.75")
    void deveCalcularCorretamenteParaMultiplosNiveis(int level, int expected) {
        // When
        Integer result = platform.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result, 
            String.format("Level %d deveria retornar %d, mas retornou %d", level, expected, result));
    }

    @Test
    @DisplayName("Deve calcular corretamente com progressBasePoints diferente")
    void deveCalcularCorretamenteComProgressBasePointsDiferente() {
        // Given
        Platform platformCustom = new Platform("custom-platform", 500, 0.75);
        int level = 2;
        // Fórmula: 500 * (1 + 0.75) = 500 * 1.75 = 875
        Integer expected = 875;

        // When
        Integer result = platformCustom.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve calcular corretamente com progressFormula diferente")
    void deveCalcularCorretamenteComProgressFormulaDiferente() {
        // Given
        Platform platformCustom = new Platform("custom-platform", 1000, 0.5);
        int level = 2;
        // Fórmula: 1000 * (1 + 0.5) = 1000 * 1.5 = 1500
        Integer expected = 1500;

        // When
        Integer result = platformCustom.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve calcular corretamente com progressFormula maior que 1")
    void deveCalcularCorretamenteComProgressFormulaMaiorQue1() {
        // Given
        Platform platformCustom = new Platform("custom-platform", 1000, 1.5);
        int level = 2;
        // Fórmula: 1000 * (1 + 1.5) = 1000 * 2.5 = 2500
        Integer expected = 2500;

        // When
        Integer result = platformCustom.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve calcular corretamente com progressFormula zero")
    void deveCalcularCorretamenteComProgressFormulaZero() {
        // Given
        Platform platformCustom = new Platform("custom-platform", 1000, 0.0);
        int level = 2;
        // Fórmula: 1000 * (1 + 0.0) = 1000 * 1.0 = 1000
        Integer expected = 1000;

        // When
        Integer result = platformCustom.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }


    @Test
    @DisplayName("Deve lançar NullPointerException quando progressBasePoints for null")
    void deveLancarNullPointerExceptionQuandoProgressBasePointsForNull() {
        // Given
        Platform platformNull = new Platform("null-platform", null, 0.75);
        int level = 2;

        // When/Then
        assertThrows(NullPointerException.class, () -> {
            platformNull.calculateLevelPoints(level);
        });
    }

    @Test
    @DisplayName("Deve lançar NullPointerException quando progressFormula for null")
    void deveLancarNullPointerExceptionQuandoProgressFormulaForNull() {
        // Given
        Platform platformNull = new Platform("null-platform", 1000, null);
        int level = 2;

        // When/Then
        assertThrows(NullPointerException.class, () -> {
            platformNull.calculateLevelPoints(level);
        });
    }

    @Test
    @DisplayName("Deve retornar progressBasePoints mesmo quando progressFormula for null para level 1")
    void deveRetornarProgressBasePointsMesmoQuandoProgressFormulaForNullParaLevel1() {
        // Given
        Platform platformNull = new Platform("null-platform", 1000, null);
        int level = 1;
        Integer expected = 1000;

        // When
        Integer result = platformNull.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve calcular corretamente com valores pequenos")
    void deveCalcularCorretamenteComValoresPequenos() {
        // Given
        Platform platformSmall = new Platform("small-platform", 10, 0.1);
        int level = 2;
        // Fórmula: 10 * (1 + 0.1) = 10 * 1.1 = 11
        Integer expected = 11;

        // When
        Integer result = platformSmall.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve calcular corretamente com valores grandes")
    void deveCalcularCorretamenteComValoresGrandes() {
        // Given
        Platform platformLarge = new Platform("large-platform", 10000, 0.9);
        int level = 2;
        // Fórmula: 10000 * (1 + 0.9) = 10000 * 1.9 = 19000
        Integer expected = 19000;

        // When
        Integer result = platformLarge.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve retornar valor inteiro mesmo quando cálculo resultar em decimal")
    void deveRetornarValorInteiroMesmoQuandoCalculoResultarEmDecimal() {
        // Given
        Platform platformDecimal = new Platform("decimal-platform", 100, 0.33);
        int level = 2;
        // Fórmula: 100 * (1 + 0.33) = 100 * 1.33 = 133.0 (inteiro)
        Integer expected = 133;

        // When
        Integer result = platformDecimal.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Deve calcular corretamente com progressFormula negativo")
    void deveCalcularCorretamenteComProgressFormulaNegativo() {
        // Given
        Platform platformNegative = new Platform("negative-platform", 1000, -0.25);
        int level = 2;
        // Fórmula: 1000 * (1 + (-0.25)) = 1000 * 0.75 = 750
        Integer expected = 750;

        // When
        Integer result = platformNegative.calculateLevelPoints(level);

        // Then
        assertEquals(expected, result);
    }
}

