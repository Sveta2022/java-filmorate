package ru.yandex.practicum.javafilmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.Film;
import ru.yandex.practicum.javafilmorate.model.User;
import ru.yandex.practicum.javafilmorate.service.UserService;
import ru.yandex.practicum.javafilmorate.storage.user.UserStorage;


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
    UserService userService;
    private long idgenerator;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //создание пользователя;
    @PostMapping
    public User create(@RequestBody User user) {
        log.info("Пользователь " + user.getName() + " id " + user.getId() + " добавлен");
        ++idgenerator;
        user.setId(idgenerator);
        validateUser(user);
        return userService.create(user);
    }

    //обновление пользователя;
    @PutMapping
    public User update(@RequestBody @Valid User user) {
        log.info("Пользователь " + user.getName() + " id " + user.getId() + " обнавлен");
        long userId = user.getId();
        if (userId < 0 && !userService.getAllUsers().contains(user)) {
            throw new NotFoundObjectException("Такого id нет");
        }
        validateUser(user);
        return userService.update(user);
    }

    //получение списка всех пользователей
    @GetMapping
    public ArrayList<User> getAllUsers() {
        log.info("Получен запрос на получение списка всех пользователей");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id){
        log.info("Получен запрос на поиск пользователя с id: " + id);
        return userService.getUserById(id);
    }


    public void validateUser(User user) {
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
}
