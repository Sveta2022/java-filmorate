package ru.yandex.practicum.javafilmorate.controller;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

//Класс тестирует контроллер UserController

@SpringBootTest
class UserControllerTest {

    private UserController userController = new UserController();

    @Test
    void create() {
        User user1 = new User(3, "user1@mail.ru", "userLogin", "userName",
                LocalDate.of(2000, 12, 25));
        userController.create(user1);
        assertEquals(1, user1.getId(), "создать пользователя и проверить его id");
    }

    @Test
    void createMailWrong() {
        User user1 = new User(3, "user1.mail.ru", "userLogin", "userName",
                LocalDate.of(2000, 12, 25));

        ValidationException valid = assertThrows(ValidationException.class, () -> userController.create(user1));
        assertEquals("электронная почта не может быть пустой и должна содержать символ @",
                valid.getMessage(), "создать пользователя и проверить его Email");
    }

    @Test
    void createLoginWrong() {
        User user1 = new User(3, "user1@mail.ru", "user Login", "userName",
                LocalDate.of(2000, 12, 25));

        ValidationException valid = assertThrows(ValidationException.class, () -> userController.create(user1));
        assertEquals("логин не может быть пустым и содержать пробелы",
                valid.getMessage(), "создать пользователя и проверить его login");
    }

    @Test
    void createNameWrong() {
        User user1 = new User(3, "user1@mail.ru", "userLogin", "",
                LocalDate.of(2000, 12, 25));
        userController.create(user1);
        assertEquals("userLogin",
                user1.getName(), "создать пользователя и проверить его name");
    }

    @Test
    void createBirthdayWrong() {
        User user1 = new User(3, "user1@mail.ru", "userLogin", "userName",
                LocalDate.of(2025, 12, 25));
        ValidationException valid = assertThrows(ValidationException.class, () -> userController.create(user1));
        assertEquals("дата рождения не может быть в будущем.",
                valid.getMessage(), "создать пользователя и проверить его дату дня Рождения");
    }

    @Test
    void update() {
        User user1 = new User(1, "user1@mail.ru", "userLogin", "userName",
                LocalDate.of(2000, 12, 25));
        userController.create(user1);
        User user2 = new User(2, "user2@mail.ru", "user2Login", "user2Name",
                LocalDate.of(2002, 12, 25));
        userController.create(user2);
        User user3 = new User(2, "user31@mail.ru", "user3Login", "user3Name",
                LocalDate.of(2000, 12, 25));
        userController.update(user3);
        assertEquals(2, userController.getAllUsers().size(), "обновить пользователя с id=2");
    }

    @Test
    void getAllUsers() {
        User user1 = new User(1, "user1@mail.ru", "userLogin", "userName",
                LocalDate.of(2000, 12, 25));
        userController.create(user1);
        User user2 = new User(2, "user2@mail.ru", "user2Login", "user2Name",
                LocalDate.of(2002, 12, 25));
        userController.create(user2);
        assertEquals(2, userController.getAllUsers().size(), "получить список всех пользователей");
    }
}