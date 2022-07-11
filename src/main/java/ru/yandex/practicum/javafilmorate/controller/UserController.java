package ru.yandex.practicum.javafilmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //создание пользователя;
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    //обновление пользователя;
    @PutMapping
    public User update(@RequestBody @Valid User user) {
        return userService.update(user);
    }

    //получение списка всех пользователей
    @GetMapping
    public ArrayList<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
