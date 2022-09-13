package ru.yandex.practicum.javafilmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.javafilmorate.JavaFilmorateApplication;
import ru.yandex.practicum.javafilmorate.model.Film;
import ru.yandex.practicum.javafilmorate.model.Genres;
import ru.yandex.practicum.javafilmorate.model.MpaRating;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@EnableAutoConfiguration
class FilmDbStorageTest extends JavaFilmorateApplication {

   private final FilmDbStorage filmStorage;
    private final MpaDbStorage mpa;
    Optional<Film>film1 = Optional.of(new Film(1, "film1", "description1",
            LocalDate.of(2002, 4, 15), 30, 2, new MpaRating(1, "GP"),
            Set.of(new Genres(1, "Драмма"), new Genres(2, "Коммедия"),
                    new Genres(3, "Ужастик"))));

    @Test
    void create() {
        filmStorage.create(film1.get());
        assertThat(film1)
                .isPresent()
                .hasValueSatisfying(film ->
                        assertThat(film).hasFieldOrPropertyWithValue("id", 1L));
    }

    @Test
    void update() {
        Optional<Film>filmNew = Optional.of(new Film(1, "filmNew", "descriptionNew",
                LocalDate.of(2015, 5, 20), 90, 22, mpa.getMpaRatingId(1).get(0),
                null));
        filmStorage.update(filmNew.get());
        assertThat(filmNew)
                .isPresent()
                .hasValueSatisfying(film ->
                        assertThat(film).hasFieldOrPropertyWithValue("name", "filmNew"));

    }

}