package ru.yandex.practicum.javafilmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.User;
import ru.yandex.practicum.javafilmorate.storage.user.UserStorage;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;

/*
добавление в друзья, удаление из друзей, вывод списка общих друзей
 */
@Service
@Slf4j
public class UserService {
    UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    private long idgenerator;

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

    public User create(User user) {
        log.info("Пользователь " + user.getName() + " id " + user.getId() + " добавлен");
        ++idgenerator;
        user.setId(idgenerator);
        validateUser(user);
        return userStorage.create(user);
    }

    //обновление пользователя;

    public User update(User user) {
        log.info("Пользователь " + user.getName() + " id " + user.getId() + " обнавлен");
        long userId = user.getId();
        if (userId < 0 && !userStorage.users.containsKey(userId)) {
            throw new ValidationException("Такого id нет");
        }
        validateUser(user);
        return userStorage.update(user);
    }

    //получение списка всех пользователей

    public ArrayList<User> getAllUsers() {
        return userStorage.getAllUsers();
    }
}
