package ru.yandex.practicum.javafilmorate.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.javafilmorate.dao.FriendsStorage;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.User;
import ru.yandex.practicum.javafilmorate.dao.UserStorage;


import java.util.*;
import java.util.stream.Collectors;

/*
добавление в друзья, удаление из друзей, вывод списка общих друзей
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserStorage userStorage;
    private final FriendsStorage friendsStorage;


    public User create(User user) {
        return userStorage.create(user);
    }

    //обновить пользователя;
    public User update(User user) {

        return userStorage.update(user);
    }

    public User getUserById(long id) {
            return userStorage.getUserById(id);
    }

    //получить список всех пользователей
    public List<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    //добавить пользователя в друзья
    public void addFriends(long userId, long friendId) {
        User user = getUserById(userId);
        User friend = getUserById(friendId);
        friendsStorage.addFriends(userId, friendId);
    }

    //удалить пользователя из друзей
    public void removeFriends(long userId, long friendId) {
        User user = getUserById(userId);
        User friend = getUserById(friendId);
        friendsStorage.removeFriends(userId, friendId);
    }

    //получить список друзей пользователя
    public Set<User> userGetFriends(long id) {
        return userStorage.getAllFriends(id);
    }

    //получить список общих друзей пользователя
    public List<User> commonFriends(long idUser, long idOther) {
        return userStorage.commonFriends(idUser, idOther);
    }
}
