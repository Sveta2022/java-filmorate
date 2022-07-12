package ru.yandex.practicum.javafilmorate.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.Film;
import ru.yandex.practicum.javafilmorate.service.FilmService;
import ru.yandex.practicum.javafilmorate.storage.film.FilmStorage;
import ru.yandex.practicum.javafilmorate.storage.film.InMemoryFilmStorage;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

//Класс тестирует контроллер FilmController

class FilmControllerTest {
    FilmStorage storage = new InMemoryFilmStorage();
    FilmService service = new FilmService(storage);
    private FilmController controller = new FilmController(service);

    @Test
    void create() {
        Film film = new Film(3, "Film1", "description film1", LocalDate.MAX, 14, null);
        controller.create(film);
        assertEquals(1, film.getId(), "создать фильм и проверить его id ");

        System.out.println(controller.getFilmById(1));
    }
//
//    @Test
//    void createFilmwithWrongDate() {
//        Film film = new Film(3, "Film1", "description film1",
//                LocalDate.of(1894, 12, 28), 14);
//        ValidationException valid = assertThrows(ValidationException.class, () ->
//                controller.create(film));
//        assertEquals("дата релиза — не раньше 28 декабря 1895 года;",
//                valid.getMessage(), "создать фильм датой раньше 28 декабря 1895");
//    }
//
//    @Test
//    void update() {
//        Film film1 = new Film(1, "Film1", "description film1",
//                LocalDate.of(1994, 12, 28), 20);
//        controller.create(film1);
//        Film film2 = new Film(2, "Film2", "description film2",
//                LocalDate.of(2020, 12, 28), 30);
//        controller.create(film2);
//        Film film3 = new Film(1, "Film3", "description film3",
//                LocalDate.of(2022, 12, 28), 40);
//        controller.update(film3);
//        assertEquals(2, controller.getFilms().size(), "обновить фильм по id");
//    }
//
//    @Test
//    void getFilms() {
//        Film film1 = new Film(1, "Film1", "description film1",
//                LocalDate.of(1994, 12, 28), 20);
//        controller.create(film1);
//        Film film2 = new Film(2, "Film2", "description film2",
//                LocalDate.of(2020, 12, 28), 30);
//        controller.create(film2);
//        assertEquals(2, controller.getFilms().size(), "получить список всех фильмов");
//    }
}