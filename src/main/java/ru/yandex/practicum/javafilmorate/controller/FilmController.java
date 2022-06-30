package ru.yandex.practicum.javafilmorate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/films")
public class FilmController {
    private Map<Integer, Film> films;
    private static final Logger log = LoggerFactory.getLogger(FilmController.class);
    private int idgenerator;

   private void validateFilm(Film film){
       //название не может быть пустым;
       //максимальная длина описания — 200 символов;
       // дата релиза — не раньше 28 декабря 1895 года;
       if(film.getReleaseDate().isBefore(LocalDate.of(1895,12,28))){
           throw new ValidationException(" дата релиза — не раньше 28 декабря 1895 года;");
       }
       //продолжительность фильма должна быть положительной.

   }

    //добавление фильма;
     @PostMapping
    public Film create(@RequestBody @Valid Film film) {
         log.info("Получен запрос на создание фильма");
         ++idgenerator;
         film.setId(idgenerator);
         validateFilm(film);
         films = new HashMap<>();
        films.put(idgenerator, film);
         System.out.println(film);
        return film;
    }

    //обновление фильма;
    @PutMapping
    public Film update(@RequestBody @Valid Film film) {
        log.info("Получен запрос на обновление фильма");
       if(film.getId()<0){
           throw new ValidationException("Отрицательное значение id");
       }
       validateFilm(film);
        films.put(film.getId(), film);
        return film;
    }

//    получение всех фильмов
    @GetMapping
    ArrayList getFilms() {
        log.info("Получен запрос на получение списка всех фильмов");

        return new ArrayList<>(films.values());
    }
}
