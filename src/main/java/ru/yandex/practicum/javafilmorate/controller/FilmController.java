package ru.yandex.practicum.javafilmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.Film;
import ru.yandex.practicum.javafilmorate.service.FilmService;
import ru.yandex.practicum.javafilmorate.service.UserService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

/*
Класс контроллер описывает эндпоинты для класса Film:
 - добавление фильма;
 - обновление фильма;
 - получение всех фильмов.
 - добавить/удалить likes
 - получить список Топ-10 фильмов
 */

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    private FilmService filmService;
    private UserService userService;

    @Autowired
    public FilmController(FilmService filmService, UserService userService) {
        this.filmService = filmService;
        this.userService = userService;
    }

    public void validateFilm(Film film) {
        // дата релиза — не раньше 28 декабря 1895 года;
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("дата релиза — не раньше 28 декабря 1895 года;");
        }
    }

    //добавление фильма;
    @PostMapping
    public Film create(@RequestBody @Valid Film film) {
        log.info("Фильм: " + film.getName() + " создан с id: " + film.getId());
        validateFilm(film);
        return filmService.create(film);
    }

    //обновление фильма;
    @PutMapping
    public Film update(@RequestBody @Valid Film film) {
        log.info("Получен запрос на обновление фильма: " + film.getName() + " с id: " + film.getId());
        validateFilm(film);
        return filmService.update(film);
    }

    //получение всех фильмов
    @GetMapping
    ArrayList getFilms() {
        log.info("Получен запрос на получение списка всех фильмов");
        return filmService.getFilms();
    }

    //получить фильм по id
    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable long id) {
        log.info("Получен запрос на поиск фильма с id: " + id);
        return filmService.getFilmById(id);
    }

    //поставить like от пользователя по id
    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable long id, @PathVariable long userId) {
        log.info("Получен запрос на лайк для фильма с id " + id);
        filmService.addLike(id, userId);
    }

    //удалить like от пользователя по id
    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike(@PathVariable long id, @PathVariable long userId) {
        log.info("Получен запрос на удаление лайка для фильма с id " + id);
        filmService.deleteLike(id, userId);
    }

    //получить список из 10-Топ фильмов
    @GetMapping("/popular")
    public Set<Film> topFilm(@RequestParam(defaultValue = "10") int count) {
        log.info("Получен запрос на список популярных фильмов");
        return filmService.topFilm(count);
    }
}
