package ru.yandex.practicum.javafilmorate.storage.user;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.javafilmorate.model.User;


import java.util.ArrayList;


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
