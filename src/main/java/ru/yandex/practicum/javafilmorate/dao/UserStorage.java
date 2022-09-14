package ru.yandex.practicum.javafilmorate.dao;

import ru.yandex.practicum.javafilmorate.model.Friends;
import ru.yandex.practicum.javafilmorate.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/*
Интерфейс определяет методы:
- создать пользователя
- обновить пользователя
- получить список пользователей
- получить пользователя по id
 */


public interface UserStorage {
    //создать пользователя
    User create(User user);

    //обновить пользователя
    User update(User user);

    //получить список всех пользователей
    List<User> getAllUsers();

    //получить пользователя по id
    User getUserById(long id);

    //получить всех друзей пользователя по его id
    Set<User> getAllFriends(long id);

    //получить список общих друзей
    List<User> commonFriends(long idUser, long idOther);
}
