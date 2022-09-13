package ru.yandex.practicum.javafilmorate.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.javafilmorate.model.Film;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
Интерфейс определяет методы:
- создать фильм
- обновить фильм
- получить список фильмов
- получить фильм по id
 */


public interface FilmStorage {
    //создать фильм
    Film create(Film film);

    //обновить фильм
    Film update(Film film);

    //получить список фильмов
    List<Film> getFilms();

    //получить фильм по id
    public Film getFilmById(long id);

    //добавить лайк фильму
    void addLike(long filmId, long userId);

    //удалить лайк фильма
    void deleteLike(long filmId, long userId);

    //вывести топ популярных фильмов
    List<Film> topFilm(int count);
}
