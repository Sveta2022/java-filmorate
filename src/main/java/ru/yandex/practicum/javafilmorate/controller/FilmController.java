package ru.yandex.practicum.javafilmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.Film;
import ru.yandex.practicum.javafilmorate.model.User;
import ru.yandex.practicum.javafilmorate.service.FilmService;
import ru.yandex.practicum.javafilmorate.service.UserService;
import ru.yandex.practicum.javafilmorate.storage.film.FilmStorage;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

/*
Класс контроллер описывает эндпоинты для класса Film:
 - добавление фильма;
 - обновление фильма;
 - получение всех фильмов.
 */

@Slf4j
@RestController
@RequestMapping("/films")
public class FilmController {

    FilmService filmService;
    @Autowired
    UserService userService;


    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
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
        if (film.getId() < 0 && !filmService.getFilms().contains(film)) {
            throw new NotFoundObjectException("Фильма с id "+ film.getId() + " нет");
        }else {
            validateFilm(film);
        return filmService.update(film);}
    }

    //    получение всех фильмов
    @GetMapping
    ArrayList getFilms() {
        log.info("Получен запрос на получение списка всех фильмов");
        return filmService.getFilms();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable long id){
        log.info("Получен запрос на поиск фильма с id: " + id);
        return filmService.getFilmById(id);
    }
    @PutMapping("/{id}/like/{userId}")
    public void addLike(@PathVariable long id, @PathVariable long userId){
        log.info("Получен запрос на лайк для фильма с id " + id);
        Film film = filmService.getFilmById(id);
        User user = userService.getUserById(userId);
        filmService.addLike(film, user);
    }
    @DeleteMapping("/{id}/like/{userId}")
    public void deleteLike (@PathVariable long id, @PathVariable long userId){
        log.info("Получен запрос на удаление лайка для фильма с id " + id);
        Film film = filmService.getFilmById(id);
        User user = userService.getUserById(userId);
        filmService.deleteLike(film, user);
    }

    @GetMapping("/popular")
    public Set<Film> topTenFilm (@RequestParam (required = false) String count){
        log.info("Получен запрос на список популярных фильмов");
        return filmService.topTenFilm(count);
    }


}
