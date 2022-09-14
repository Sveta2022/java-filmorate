package ru.yandex.practicum.javafilmorate.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.javafilmorate.dao.GenreStorage;
import ru.yandex.practicum.javafilmorate.dao.impl.FilmDbStorage;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.Film;
import ru.yandex.practicum.javafilmorate.model.User;
import ru.yandex.practicum.javafilmorate.dao.FilmStorage;
import ru.yandex.practicum.javafilmorate.dao.UserStorage;

import java.util.*;

/*
Класс отвечает за операции с фильмами, — добавление и удаление лайка, вывод 10 наиболее популярных
фильмов по количеству лайков.
 */
@Service
@RequiredArgsConstructor
public class FilmService {
    private final FilmStorage filmStorage;

    //создать фильм
    public Film create(Film film) {
        return filmStorage.create(film);
    }

    //обновить фильм;
    public Film update(Film film) {
        Film filmOld = getFilmById(film.getId());
        return filmStorage.update(film);
    }

    //полученить список всех фильмов
    public List<Film> getFilms() {
        return filmStorage.getFilms();
    }

    //получить фильм по id
    public Film getFilmById(long id) {
        Film film = filmStorage.getFilmById(id);
        return film;
    }

    //добавить like
    public void addLike(long filmId, long userId) {
        if (userId < 0) {
            throw new NotFoundObjectException("Нет пользователя с id " + userId);
        }
        filmStorage.addLike(filmId, userId);
        Film film = getFilmById(filmId);
        film.setRate(film.getRate() + 1);
        filmStorage.update(film);
    }

    //удалить like
    public void deleteLike(long filmId, long userId) {
        if (userId < 0) {
            throw new NotFoundObjectException("Нет пользователя с id " + userId);
        }
        filmStorage.deleteLike(filmId, userId);
        Film film = getFilmById(filmId);
        if (film.getRate() == 0) {
            return;
        }
        film.setRate(film.getRate() - 1);
        filmStorage.update(film);
    }

    //получить список Топ-10 фильмов
    public List<Film> topFilm(int count) {
        return filmStorage.topFilm(count);
    }
}
