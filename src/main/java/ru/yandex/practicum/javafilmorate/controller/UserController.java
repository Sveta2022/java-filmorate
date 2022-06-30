package ru.yandex.practicum.javafilmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.User;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



@RestController
@RequestMapping("/users")

public class UserController {
    private Map<Integer, User> users = new HashMap<>();
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);
    private static int idgenerator;

    private void validate(User user) {
        //электронная почта не может быть пустой и должна содержать символ @
        String userEmail = user.getEmail();
        boolean mailFormat = userEmail.contains("@");
        if (userEmail.isEmpty() || !mailFormat) {
            log.info("почта имеет ошибку");
            throw new ValidationException("электронная почта не может быть пустой и должна содержать символ @");
        }
        //логин не может быть пустым и содержать пробелы;
        String userLogin = user.getLogin();
        boolean loginFormat = userLogin.contains(" ");
        if (userLogin.isBlank() || loginFormat) {
            log.info("логин имеет ошибку");
            throw new ValidationException("логин не может быть пустым и содержать пробелы");
        }
        //имя для отображения может быть пустым — в таком случае будет использован логин;
        String userName = user.getName();
        if(userName.isEmpty()){
            user.setName(userLogin);
        }

        // дата рождения не может быть в будущем.
        if(user.getBirthday().isAfter(LocalDate.now())){
            throw new RuntimeException("дата рождения не может быть в будущем.");
        }
    }

    //    создание пользователя;
    @PostMapping
    public User create(@RequestBody User user) {
        log.info("Получен запрос на создание пользователя");
        ++idgenerator;
        user.setId(idgenerator);
        validate(user);
        users.put(user.getId(), user);
        return user;
    }

    //    обновление пользователя;
    @PutMapping
    public User update(@RequestBody @Valid User user) {
        log.info("Получен запрос на обновление пользователя");
        int userId = user.getId();
        if(userId<0 && !users.containsKey(userId)){
            throw new ValidationException("Такого id нет");
        }
        validate(user);
        users.put(user.getId(), user);
        return user;
    }

    //    получение списка всех пользователей
    @GetMapping
    public ArrayList<User> getAllUsers() {
        log.info("Получен запрос на получение списка всех пользователей");
        return new ArrayList<>(users.values());

    }

}
