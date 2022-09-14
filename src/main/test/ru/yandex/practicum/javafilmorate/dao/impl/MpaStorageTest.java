package ru.yandex.practicum.javafilmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.javafilmorate.JavaFilmorateApplication;
import ru.yandex.practicum.javafilmorate.dao.MpaStorage;
import ru.yandex.practicum.javafilmorate.model.MpaRating;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@EnableAutoConfiguration
class MpaStorageTest extends JavaFilmorateApplication {

    private final MpaStorage mpaStorage;

    @Test
    public void getMpaRatingId() {
        Optional<MpaRating> mpa = Optional.ofNullable(mpaStorage.getMpaRatingId(3).get(0));

        assertThat(mpa)
                .isPresent()
                .hasValueSatisfying(mpa1 ->
                        assertThat(mpa1).hasFieldOrPropertyWithValue("id", 3));

    }
}