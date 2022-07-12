package ru.yandex.practicum.javafilmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.Film;
import ru.yandex.practicum.javafilmorate.service.FilmService;
import ru.yandex.practicum.javafilmorate.storage.film.FilmStorage;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;

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
    private long idgenerator;

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
        ++idgenerator;
        film.setId(idgenerator);
        validateFilm(film);
        return filmService.create(film);
    }

    //обновление фильма;
    @PutMapping
    public Film update(@RequestBody @Valid Film film) {
        log.info("Фильм: " + film.getName() + " обнавлен с id: " + film.getId());
        if (film.getId() < 0) {
            throw new ValidationException("Отрицательное значение id");
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


}
