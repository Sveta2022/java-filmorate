package ru.yandex.practicum.javafilmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.javafilmorate.dao.UserStorage;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.User;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Repository
@Slf4j
@RequiredArgsConstructor
@Primary
public class UserDbStorage implements UserStorage {
    private final JdbcTemplate jdbcTemplate;

    static User makeUser(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getLong("ID"),
                rs.getString("EMAIL"),
                rs.getString("LOGIN"),
                rs.getString("NAME"),
                rs.getDate("BIRTHDAY").toLocalDate());
    }

    @Override
    public User create(User user) {

        String sqlQuery = "insert into USERS (EMAIL, LOGIN, NAME, BIRTHDAY) values (?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"ID"});
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getName());
            final LocalDate birthday = user.getBirthday();
            if (birthday == null) {
                stmt.setNull(4, Types.DATE);
            } else {
                stmt.setDate(4, Date.valueOf(birthday));
            }
            return stmt;
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public User update(User user) {
        String sqlQuery = "update USERS set " +
                "EMAIL = ?, NAME = ?, LOGIN = ?, BIRTHDAY = ?" +
                "where ID = ?";
        jdbcTemplate.update(sqlQuery
                , user.getEmail()
                , user.getName()
                , user.getLogin()
                , user.getBirthday()
                , user.getId());
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        final String sqlQuery = "SELECT ID, EMAIL, NAME, LOGIN, BIRTHDAY FROM USERS";
        final List<User> users = jdbcTemplate.query(sqlQuery, UserDbStorage::makeUser);
        return users;

    }

    @Override
    public User getUserById(long id) {
        final String sqlQuery = "SELECT ID, EMAIL, NAME, LOGIN, BIRTHDAY FROM USERS " +
                "WHERE ID = ?";
        final List<User> users = jdbcTemplate.query(sqlQuery, UserDbStorage::makeUser, id);
        if(users.size()!=1){
            throw new NotFoundObjectException("Пользователя с id " + id + " нет в списке");
        }
        return users.get(0);
    }

    @Override
    public Set<User> getAllFriends(long id) {
        String sqlQuery = "SELECT ID, EMAIL, NAME, LOGIN, BIRTHDAY FROM FRIENDS F JOIN USERS U on U.ID = F.ID_FRIENDS " +
                "WHERE ID_USERS = ?";
        List<User> friends = jdbcTemplate.query(sqlQuery, UserDbStorage::makeUser, id);

        return friends.stream().collect(Collectors.toSet());
    }

    @Override
    public List<User> commonFriends(long idUser, long idOther) {

        String sqlQuery = "SELECT ID_FRIENDS FROM FRIENDS JOIN USERS U on U.ID = FRIENDS.ID_USERS WHERE ID_USERS = ?";

        List<Long> userFriends = jdbcTemplate.queryForList(sqlQuery, Long.class, idUser);
        List<Long> otherFriends = jdbcTemplate.queryForList(sqlQuery, Long.class, idOther);
        List<Long> commonUserFriendsById = new ArrayList<>(userFriends);
        commonUserFriendsById.retainAll(otherFriends);

        return commonUserFriendsById
                .stream()
                .map(userId -> getUserById(userId))
                .collect(Collectors.toList());
    }
}

