package ru.yandex.practicum.javafilmorate.storage.user;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@Getter
@Setter
public class InMemoryUserStorage implements UserStorage {

    //создание пользователя;

    @Override
    public User create(User user) {
        users.put(user.getId(), user);
        return user;
    }

    //обновление пользователя;

    @Override
    public User update(User user) {
        users.put(user.getId(), user);
        return user;
    }

    //получение списка всех пользователей
    @Override
    public ArrayList<User> getAllUsers() {
        log.info("Получен запрос на получение списка всех пользователей");
        return new ArrayList<>(users.values());
    }

}
