package ru.yandex.practicum.javafilmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.javafilmorate.dao.FriendsStorage;
import ru.yandex.practicum.javafilmorate.model.User;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
@Primary
public class FriendsDbStorage implements FriendsStorage {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public void addFriends(long userId, long friendId) {
        String sqlQuery = "insert into FRIENDS (ID_USERS, ID_FRIENDS) values (?, ?)";
        jdbcTemplate.update(sqlQuery, userId, friendId);
    }

    @Override
    public void removeFriends(long userId, long friendId) {
        String sqlQuery = "delete from FRIENDS WHERE ID_USERS = ? AND ID_FRIENDS = ?";
        jdbcTemplate.update(sqlQuery, userId, friendId);
    }

}
