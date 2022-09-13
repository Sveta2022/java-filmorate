package ru.yandex.practicum.javafilmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.javafilmorate.JavaFilmorateApplication;
import ru.yandex.practicum.javafilmorate.dao.impl.UserDbStorage;
import ru.yandex.practicum.javafilmorate.model.User;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@EnableAutoConfiguration
class UserDbStorageTest extends JavaFilmorateApplication {

  private final UserDbStorage userStorage;
  Optional <User> user1 = Optional.of(new User(1L, "userEmail1", "userLogin1", "userName1",
          LocalDate.of(2015, 3, 31)));
    Optional <User> user2 = Optional.of(new User(2L, "userEmail2", "userLogin2", "userName2",
            LocalDate.of(2012, 6, 15)));
    Optional <User> user3 = Optional.of(new User(3L, "userEmail3", "userLogin3", "userName3",
            LocalDate.of(200, 2, 8)));

    @Test
    void create() {
        userStorage.create(user1.get());

        assertThat(user1)
                .isPresent()
                .hasValueSatisfying(userNew ->
                        assertThat(userNew).hasFieldOrPropertyWithValue("id", 1L)
                );
    }

    @Test
    void update() {
        Optional <User> userUpdated = Optional.of(new User(1L, "userEmailUpdated", "userLoginUpdated",
                "userNameUpdated", LocalDate.of(2010, 11, 10)));
        userStorage.create(userUpdated.get());
        userStorage.update(userUpdated.get());

        assertThat(userUpdated)
                .isPresent()
                .hasValueSatisfying(user1 ->
                        assertThat(user1).hasFieldOrPropertyWithValue("email", "userEmailUpdated")
                );

    }

    @Test
    void getUserById() {
        userStorage.create(user1.get());
        userStorage.create(user2.get());
        userStorage.create(user3.get());

        assertThat(user3)
                .isPresent()
                .hasValueSatisfying(user ->
                        assertThat(user).hasFieldOrPropertyWithValue("id", 3L)
                );

    }
}