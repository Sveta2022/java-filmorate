package ru.yandex.practicum.javafilmorate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.Film;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

//Класс тестирует контроллер FilmController

class FilmControllerTest {
    private FilmController controller = new FilmController();

    @Test
    void create() {
        Film film = new Film(3, "Film1", "description film1", LocalDate.MAX, 14);
        controller.create(film);
        assertEquals(1, film.getId(), "создать фильм и проверить его id ");
    }

    @Test
    void createFilmwithWrongDate() {
        Film film = new Film(3, "Film1", "description film1",
                LocalDate.of(1894, 12, 28), 14);
        ValidationException valid = assertThrows(ValidationException.class, () ->
                controller.create(film));
        assertEquals("дата релиза — не раньше 28 декабря 1895 года;",
                valid.getMessage(), "создать фильм датой раньше 28 декабря 1895");
    }

    @Test
    void update() {
        Film film1 = new Film(1, "Film1", "description film1",
                LocalDate.of(1994, 12, 28), 20);
        controller.create(film1);
        Film film2 = new Film(2, "Film2", "description film2",
                LocalDate.of(2020, 12, 28), 30);
        controller.create(film2);
        Film film3 = new Film(1, "Film3", "description film3",
                LocalDate.of(2022, 12, 28), 40);
        controller.update(film3);
        assertEquals(2, controller.getFilms().size(), "обновить фильм по id");
    }

    @Test
    void getFilms() {
        Film film1 = new Film(1, "Film1", "description film1",
                LocalDate.of(1994, 12, 28), 20);
        controller.create(film1);
        Film film2 = new Film(2, "Film2", "description film2",
                LocalDate.of(2020, 12, 28), 30);
        controller.create(film2);
        assertEquals(2, controller.getFilms().size(), "получить список всех фильмов");
    }
}