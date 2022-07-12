package ru.yandex.practicum.javafilmorate.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.User;
import ru.yandex.practicum.javafilmorate.storage.user.UserStorage;


import java.util.ArrayList;

import java.util.Optional;
import java.util.Set;

/*
добавление в друзья, удаление из друзей, вывод списка общих друзей
 */
@Service
@Slf4j
@NoArgsConstructor
public class UserService {
    UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }


    public User create(User user) {
        return userStorage.create(user);
    }

    //обновление пользователя;

    public User update(User user) {

        return userStorage.update(user);
    }

    public User getUserById(long id){
        Optional<User> userById = getAllUsers().stream().
                filter(user->user.getId() == id).findFirst();
        if(userById.isPresent()){
            return userById.get();
        } else {
            throw new NotFoundObjectException("Пользователя с таким id нет в списке");
        }
    }

    //получение списка всех пользователей

    public ArrayList<User> getAllUsers() {
        return userStorage.getAllUsers();
    }

    public void addFriends(User user, User userFriend){
        long id = user.getId();
        long idFriend = userFriend.getId();
        user.getFriends().add(idFriend);
        userFriend.getFriends().add(id);

    }

    public void removeFriends(User user, User userFriend){
        long id = user.getId();
        long idFriend = userFriend.getId();
        user.getFriends().remove(idFriend);
        userFriend.getFriends().remove(id);
    }
    public Set<Long> commonFriends(User user){
        return user.getFriends();
    }

}
