package ru.yandex.practicum.javafilmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.User;
import ru.yandex.practicum.javafilmorate.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

/*
Класс контроллер описывает эндпоинты для класса User:
 - создание пользователя;
 - обновление пользователя;
 - получение списка всех пользователей;
 - добавить/удалить пользователя в друзья;
 - получить список обзих друзей
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")

public class UserController {
   final private UserService userService;

    //создать пользователя;
    @PostMapping
    public User create(@RequestBody User user) {
        validateUser(user);
        log.info("Запрос на добавление пользователя " + user.getName() + " id " + user.getId() + " получен");
        return userService.create(user);
    }

    //обновленить пользователя;
    @PutMapping
    public User update(@RequestBody @Valid User user) {
        log.info("Запрос на обновление пользователя " + user.getName() + " id " + user.getId() + " получен");
        long userId = user.getId();
        if (userId < 0 && !userService.getAllUsers().contains(user)) {
            throw new NotFoundObjectException("Пользователя с id " + user.getId() + " нет");
        }
        validateUser(user);
        return userService.update(user);
    }

    //полученить список всех пользователей
    @GetMapping
    public ArrayList<User> getAllUsers() {
        log.info("Получен запрос на получение списка всех пользователей");
        return userService.getAllUsers();
    }

    //получить пользователя по id
    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        log.info("Получен запрос на поиск пользователя с id: " + id);
        return userService.getUserById(id);
    }

    //добавить пользователя в друзья
    @PutMapping("/{id}/friends/{friendId}")
    public void addFriends(@PathVariable long id, @PathVariable long friendId) {
        log.info("Запрос на добавление пользователя с id " + id + " в друзья с пользователем id " + friendId);
        userService.addFriends(id, friendId);
    }

    //удалить пользователя из друзей
    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable long id, @PathVariable long friendId) {
        log.info("Запрос на удаление пользователя с id " + friendId + " из друзей пользователя id " + id);
        userService.removeFriends(id, friendId);
    }

    //вернуть список друзей пользователя с id
    @GetMapping("/{id}/friends")
    public List<User> userFriend(@PathVariable long id) {
        log.info("Запрос: вернуть список друзей пользователя с id " + id);
        return userService.userfriends(id);
    }

    //вернуть список общих друзей двух пользователей
    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> commonFriend(@PathVariable Long id, @PathVariable Long otherId) {
        log.info("Запрос: вернуть список общих друзей двух пользователей");
        return userService.commonFriends(id, otherId);
    }

    public void validateUser(User user) {
        //электронная почта не может быть пустой и должна содержать символ @
        String userEmail = user.getEmail();
        boolean mailFormat = userEmail.contains("@");
        if (userEmail.isEmpty() || !mailFormat) {
            throw new ValidationException("электронная почта не может быть пустой и должна содержать символ @");
        }
        //логин не может быть пустым и содержать пробелы;
        String userLogin = user.getLogin();
        boolean loginFormat = userLogin.contains(" ");
        if (userLogin.isBlank() || loginFormat) {
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
