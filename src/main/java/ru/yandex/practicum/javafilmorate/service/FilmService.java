package ru.yandex.practicum.javafilmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;
import ru.yandex.practicum.javafilmorate.model.Film;
import ru.yandex.practicum.javafilmorate.model.User;
import ru.yandex.practicum.javafilmorate.storage.film.FilmStorage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/*
Класс отвечает за операции с фильмами, — добавление и удаление лайка, вывод 10 наиболее популярных
фильмов по количеству лайков.
 */
@Service
@Slf4j
public class FilmService {
    FilmStorage filmStorage;
    private long idgenerator;

    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public void validateFilm(Film film) {
        // дата релиза — не раньше 28 декабря 1895 года;
        if (film.getReleaseDate().isBefore(LocalDate.of(1895, 12, 28))) {
            throw new ValidationException("дата релиза — не раньше 28 декабря 1895 года;");
        }
    }

    public Film create(Film film) {
        log.info("Фильм: " + film.getName() + " создан с id: " + film.getId());
        ++idgenerator;
        film.setId(idgenerator);
        validateFilm(film);
        return filmStorage.create(film);
    }

    //обновление фильма;
    public Film update(Film film) {
        log.info("Фильм: " + film.getName() + " обнавлен с id: " + film.getId());
        if (film.getId() < 0) {
            throw new ValidationException("Отрицательное значение id");
        }else {
        validateFilm(film);
        return filmStorage.update(film);
        }
    }

    //    получение всех фильмов
    public ArrayList getFilms() {
        log.info("Получен запрос на получение списка всех фильмов");
        return filmStorage.getFilms();
    }

    public Film getFilmById(long id){
        log.info("Получен запрос на поиск фильма с id: " + id);
       Optional<Film> filmById = filmStorage.getFilms().stream().
               filter(film->film.getId() == id).findFirst();
       if(filmById.isPresent()){
           return filmById.get();
       } else {
           throw new ValidationException("Фильма с таким id нет в списке");
       }
    }

    public void addLike(User user, Film film){
        long id = user.getId();
        film.getLikes().add(id);
    }

    public void removeLike (User user, Film film){
        long id = user.getId();
        film.getLikes().remove(id);
    }

    public Set<Film> topTenFilm(){
        ArrayList<Film> films = filmStorage.getFilms();
        Set<Film> collect = films.stream().sorted(Comparator.comparingInt(x -> x.getLikes().size()))
                .limit(10).collect(Collectors.toSet());
        return collect;
    }



}
