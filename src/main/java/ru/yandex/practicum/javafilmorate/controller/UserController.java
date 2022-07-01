package ru.yandex.practicum.javafilmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.User;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/*
Класс контроллер описывает эндпоинты для класса User:
 - создание пользователя;
 - обновление пользователя;
 - получение списка всех пользователей.
 */

@Slf4j
@RestController
@RequestMapping("/users")

public class UserController {
    private Map<Long, User> users = new HashMap<>();
    private long idgenerator;

    private void validateUser(User user) {
        //электронная почта не может быть пустой и должна содержать символ @
        String userEmail = user.getEmail();
        boolean mailFormat = userEmail.contains("@");
        if (userEmail.isEmpty() || !mailFormat) {
            log.info("почта для пользователя " + user.getName() + " c id: " + user.getId() + " имеет ошибку");
            throw new ValidationException("электронная почта не может быть пустой и должна содержать символ @");
        }
        //логин не может быть пустым и содержать пробелы;
        String userLogin = user.getLogin();
        boolean loginFormat = userLogin.contains(" ");
        if (userLogin.isBlank() || loginFormat) {
            log.info("логин имеет ошибку у пользователя " + user.getName() + " c id " + user.getId());
            throw new ValidationException("логин не может быть пустым и содержать пробелы");
        }
        //имя для отображения может быть пустым — в таком случае будет использован логин;
        String userName = user.getName();
        if (userName.isEmpty()) {
            user.setName(userLogin);
        }

        // дата рождения не может быть в будущем.
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("дата рождения не может быть в будущем.");
        }
    }

    //создание пользователя;
    @PostMapping
    public User create(@RequestBody User user) {
        log.info("Пользователь " + user.getName() + " id " + user.getId() + " добавлен");
        ++idgenerator;
        user.setId(idgenerator);
        validateUser(user);
        users.put(user.getId(), user);
        return user;
    }

    //обновление пользователя;
    @PutMapping
    public User update(@RequestBody @Valid User user) {
        log.info("Пользователь " + user.getName() + " id " + user.getId() + " обнавлен");
        long userId = user.getId();
        if (userId < 0 && !users.containsKey(userId)) {
            throw new ValidationException("Такого id нет");
        }
        validateUser(user);
        users.put(user.getId(), user);
        return user;
    }

    //получение списка всех пользователей
    @GetMapping
    public ArrayList<User> getAllUsers() {
        log.info("Получен запрос на получение списка всех пользователей");
        return new ArrayList<>(users.values());
    }
}
