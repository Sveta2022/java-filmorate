package ru.yandex.practicum.javafilmorate.storage.user;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.javafilmorate.model.User;

import java.util.ArrayList;

/*
Интерфейс определяет методы:
- создать пользователя
- обновить пользователя
- получить список пользователей
- получить пользователя по id
 */

@Component
public interface UserStorage {
    //создать пользователя
    public User create(User user);

    //обновить пользователя
    public User update(User user);

    //получить список всех пользователей
    public ArrayList<User> getAllUsers();

    //получить пользователя по id
    public User getUserById(long id);

}
