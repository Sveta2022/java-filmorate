package ru.yandex.practicum.javafilmorate.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.javafilmorate.dao.impl.FilmDbStorage;
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
    private final UserStorage userStorage;

    // private long idgenerator;


    //создать фильм
    public Film create(Film film) {
     //   ++idgenerator;
    //    film.setId(idgenerator);
        return filmStorage.create(film);
    }

    //обновленить фильм;
    public Film update(Film film) {
        return filmStorage.update(film);
    }

    //полученить список всех фильмов
    public ArrayList<Film> getFilms() {
        return filmStorage.getFilms();
    }

    //получить фильм по id
    public Film getFilmById(long id) {
        Film film = filmStorage.getFilmById(id);
        return film;
    }

    //добавить like
    public void addLike(long filmId, long userId) {
        Film film = filmStorage.getFilmById(filmId);
        User user = userStorage.getUserById(userId);
      //  film.getLikes().add(user.getId());
        update(film);
    }

    //удалить like
    public void deleteLike(long filmId, long userId) {
        Film film = filmStorage.getFilmById(filmId);
        User user = userStorage.getUserById(userId);
       // film.getLikes().remove(user.getId());
        update(film);
    }

    //получить список Топ-10 фильмов
    public Set<Film> topFilm(int count) {
        ArrayList<Film> films = filmStorage.getFilms();
        Set<Film> collect = new HashSet<>();
//        if (count == null) {
//            films.stream()
//                    .sorted(Comparator.comparingInt(Film::getCountLike).reversed())
//                    .limit(10)
//                    .forEach(collect::add);
//
//        } else {
//            films.stream()
//                    .sorted(Comparator.comparingInt(Film::getCountLike).reversed())
//                    .limit(count)
//                    .forEach(collect::add);
//        }
        return collect;
    }
}
