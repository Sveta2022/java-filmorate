package ru.yandex.practicum.javafilmorate.storage.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.javafilmorate.model.Film;

import java.util.ArrayList;

/*
Интерфейс определяет методы:
- создать фильм
- обновить фильм
- получить список фильмов
- получить фильм по id
 */

@Component
public interface FilmStorage {
    //создать фильм
    Film create(Film film);

    //обновить фильм
    Film update(Film film);

    //получить список фильмов
    ArrayList<Film> getFilms();

    //получить фильм по id
    public Film getFilmById(long id);
}
