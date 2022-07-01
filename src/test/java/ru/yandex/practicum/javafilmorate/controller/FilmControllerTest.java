package ru.yandex.practicum.javafilmorate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class FilmControllerTest {
    FilmController controller = new FilmController();

    @Test
    void create() {
        Film film = new Film(3,"Film1", "description film1", LocalDate.MAX, 14);
        controller.create(film);
        assertEquals(1, film.getId(), "создать фильм и проверить его id ");
    }

    @Test
    void createFilmwithWrongDate() {
        Film film = new Film(3,"Film1", "description film1",
                LocalDate.of(1894,12,28), 14);
        ValidationException valid = assertThrows(ValidationException.class, ()->
        controller.create(film));
        assertEquals("дата релиза — не раньше 28 декабря 1895 года;",
                valid.getMessage(), "создать фильм с пустым названием");
    }


    @Test
    void update() {
    }

    @Test
    void getFilms() {
    }
}