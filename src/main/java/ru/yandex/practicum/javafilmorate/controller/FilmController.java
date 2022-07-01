package ru.yandex.practicum.javafilmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.Film;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    private Map<Integer, Film> films = new HashMap<>();
    private int idgenerator;

   private void validateFilm(Film film){
       // дата релиза — не раньше 28 декабря 1895 года;
       if(film.getReleaseDate().isBefore(LocalDate.of(1895,12,28))){
           throw new ValidationException("дата релиза — не раньше 28 декабря 1895 года;");
       }
   }

    //добавление фильма;
     @PostMapping
    public Film create(@RequestBody @Valid Film film) {
         log.info("Получен запрос на создание фильма");
         ++idgenerator;
         film.setId(idgenerator);
         validateFilm(film);
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
