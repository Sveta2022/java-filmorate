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
import java.util.*;

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

// TODO при создание friends id = 7, а в тесте = 2 ????


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //создание пользователя;
    @PostMapping
    public User create(@RequestBody User user) {
        validateUser(user);
        log.info("Запрос на добавление пользователя " + user.getName() + " id " + user.getId() + " получен");
        return userService.create(user);

    }

    //обновление пользователя;
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

    @PutMapping("/{id}/friends/{friendId}")
    public void addFriends(@PathVariable long id, @PathVariable long friendId){
        log.info("Запрос на добавление пользователя с id " + id + " в друзья с пользователем id " + friendId);
        User user = userService.getUserById(id);
        User friend = userService.getUserById(friendId);
        userService.addFriends(user, friend);
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@PathVariable long id, @PathVariable long friendId){
        log.info("Запрос на удаление пользователя с id " + friendId + " из друзей пользователя id " + id);
        User user = userService.getUserById(id);
        User friend = userService.getUserById(friendId);
        userService.removeFriends(user, friend);
    }

    @GetMapping("/{id}/friends")
    public List<User> userFriend (@PathVariable long id){
        log.info("Запрос: вернуть список друзей пользователя с id " + id);
        User user = userService.getUserById(id);
        return userService.userfriends(user);
    }
    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> commonFriend(@PathVariable Long id, @PathVariable Long otherId){
        log.info("Запрос: вернуть список общих друзей двух пользователей");
        User user = userService.getUserById(id);
        User otherUser = userService.getUserById(otherId);
        return userService.commonFriends(user, otherUser);
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
