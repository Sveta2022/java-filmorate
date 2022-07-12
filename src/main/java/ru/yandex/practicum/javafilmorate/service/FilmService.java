package ru.yandex.practicum.javafilmorate.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.Film;
import ru.yandex.practicum.javafilmorate.model.User;
import ru.yandex.practicum.javafilmorate.storage.film.FilmStorage;
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
@NoArgsConstructor
public class FilmService {
    FilmStorage filmStorage;


    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Film create(Film film) {
        return filmStorage.create(film);
    }

    //обновление фильма;
    public Film update(Film film) {
        return filmStorage.update(film);
    }

    //    получение всех фильмов
    public ArrayList <Film> getFilms() {
        return filmStorage.getFilms();
    }

    public Film getFilmById(long id){
       Optional<Film> filmById = getFilms().stream().
               filter(film->film.getId() == id).findFirst();
       if(filmById.isPresent()){
           return filmById.get();
       } else {
           throw new NotFoundObjectException("Фильма с таким id нет в списке");
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
