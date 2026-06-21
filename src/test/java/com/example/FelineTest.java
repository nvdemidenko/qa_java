package com.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FelineTest {

    // Создаем экземпляр Feline, который будем использовать в тестах.
    // Это можно делать в каждом методе или один раз как поле класса.
    private final Feline feline = new Feline();

    @Test
    void eatMeat_ShouldReturnPredatorDiet() throws Exception {
        // Act: Вызываем метод, который хотим проверить.
        List<String> diet = feline.eatMeat();

        // Assert: Проверяем результат.
        // Метод eatMeat() в Feline просто вызывает getFood("Хищник") из класса Animal.
        // Мы ожидаем, что он вернет рацион хищника.
        assertNotNull(diet, "Рацион не должен быть null");
        assertFalse(diet.isEmpty(), "Рацион не должен быть пустым");
        assertTrue(diet.contains("Животные"), "Рацион должен содержать 'Животные'");
        assertTrue(diet.contains("Птицы"), "Рацион должен содержать 'Птицы'");
    }

    @Test
    void getFamily_ShouldReturnCatFamily() {
        // Act: Вызываем метод.
        String family = feline.getFamily();

        // Assert: Проверяем, что метод переопределен и возвращает конкретное значение.
        // В классе Animal этот метод возвращает общую строку,
        // а в Feline он переопределен для возврата "Кошачьи".
        assertEquals("Кошачьи", family, "Метод должен возвращать 'Кошачьи'");
    }

    @Test
    void getKittens_WithoutArguments_ShouldReturnDefaultValue() {
        // Act: Вызываем метод без аргументов.
        int kittens = feline.getKittens();

        // Assert: Проверяем результат.
        // Метод без аргументов по умолчанию вызывает getKittens(1).
        assertEquals(1, kittens, "По умолчанию должно возвращаться 1 котенок");
    }

    @Test
    void getKittens_WithArgument_ShouldReturnPassedValue() {
        // Arrange: Определяем тестовые данные.
        int expectedCount = 5;

        // Act: Вызываем метод с аргументом.
        int kittens = feline.getKittens(expectedCount);

        // Assert: Проверяем, что метод просто возвращает то, что ему передали.
        assertEquals(expectedCount, kittens, "Метод должен возвращать переданное ему число");
    }
}