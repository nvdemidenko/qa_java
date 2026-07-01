package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CatTest {

    @Mock
    private Feline felineMock; // Создаем заглушку для сложной зависимости

    private Cat cat; // Объект, который мы тестируем

    @BeforeEach
    void setUp() {
        // Внедряем mock-зависимость в реальный объект перед каждым тестом
        this.cat = new Cat(felineMock);
    }

    @Test
    void getSoundShouldReturnMeowWhenCalledTest() {
        // Arrange не требуется

        // Act
        String sound = cat.getSound();

        // Assert
        assertEquals("Мяу", sound, "Звук кота должен быть 'Мяу'");
    }

    @Test
    void getFoodShouldReturnResultFromEatMeatMethodTest() throws Exception {
        // Arrange - Настраиваем поведение мока для метода eatMeat()
        List<String> expectedFood = Arrays.asList("Молоко", "Сметана");
        when(felineMock.eatMeat()).thenReturn(expectedFood);

        // Act - Вызываем метод у Cat
        List<String> actualFood = cat.getFood();

        // Assert - Проверяем результат и сам вызов
        assertEquals(expectedFood, actualFood, "Cat должен вернуть еду от Feline");
        verify(felineMock).eatMeat(); // Проверяем, что был вызван нужный метод
    }

    @Test
    void eatMeatShouldUsePredatorInterfaceMethodFromFelineTest() throws Exception {
        // Arrange - Тестируем взаимодействие через интерфейс Predator
        List<String> meatMenu = Arrays.asList("Мышь", "Курица");
        when(felineMock.eatMeat()).thenReturn(meatMenu);

        // Act
        List<String> result = cat.getFood();

        // Assert
        assertEquals(meatMenu, result, "Метод eatMeat у Cat должен использовать реализацию из Feline");
        verify(felineMock).eatMeat(); // Убеждаемся, что был вызван именно этот метод
    }
}