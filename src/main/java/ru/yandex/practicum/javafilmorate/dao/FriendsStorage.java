package ru.yandex.practicum.javafilmorate.dao;

public interface FriendsStorage {

    //Добавить друга в базу
    void addFriends(long userId, long friendId);

    //удалить друга из базы
    void removeFriends(long userId, long friendId);

}
