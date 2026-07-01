package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    private Feline felineMock;

    private Lion lionMale;   // Лев-самец
    private Lion lionFemale; // Львица

    @BeforeEach
    void setUp() throws Exception {
        this.lionMale = new Lion(felineMock, "Самец");
        this.lionFemale = new Lion(felineMock, "Самка");
    }

    @Test
    void doesHaveManeMaleTrueTest() {
        boolean hasMane = lionMale.doesHaveMane();

        assertTrue(hasMane, "Лев-самец должен иметь гриву");
    }

    @Test
    void doesHaveManeFemaleFalseTest() {
        boolean hasMane = lionFemale.doesHaveMane();

        assertFalse(hasMane, "Львица не должна иметь гриву");
    }

    @Test
    void getKittensTest() throws Exception {
        when(felineMock.getKittens()).thenReturn(4);

        int kittensCount = lionMale.getKittens(); // Можно использовать любого из созданных львов

        assertEquals(4, kittensCount, "Количество котят должно соответствовать значению из Feline");
    }

    @Test
    void getFoodTest() throws Exception {
        List<String> expectedFood = List.of("Мясо", "Зебра");
        when(felineMock.getFood("Хищник")).thenReturn(expectedFood);

        List<String> actualFood = lionFemale.getFood(); // Можно использовать любого из созданных львов

        assertEquals(expectedFood, actualFood, "Рацион льва должен определяться объектом Feline");
    }

    @ParameterizedTest(name = "Лев с полом {0} должен иметь гриву {1}")
    @CsvSource({
            "Самец, true",
            "Самка, false"
    })
    void lionConstructorShouldSetManeBasedOnSexTest(String sex, boolean expectedHasMane) throws Exception {
        Feline feline = new Feline(); // Зависимость для Lion
        Lion lion = new Lion(feline, sex);

        assertEquals(expectedHasMane, lion.doesHaveMane());
    }
}