package ru.yandex.practicum.javafilmorate.service;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.Film;
import ru.yandex.practicum.javafilmorate.model.User;
import ru.yandex.practicum.javafilmorate.storage.film.FilmStorage;
import ru.yandex.practicum.javafilmorate.storage.user.UserStorage;

import java.util.*;
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
    private long idgenerator;


    @Autowired
    public FilmService(FilmStorage filmStorage) {
        this.filmStorage = filmStorage;
    }

    public Film create(Film film) {
        ++idgenerator;
        film.setId(idgenerator);
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

    public void addLike(Film film, User user){
        film.getLikes().add(user.getId());
        System.out.println(film.getCountLike());
        // TODO удалить
    }

    public void deleteLike (Film film, User user){
        film.getLikes().remove(user.getId());
    }

    public Set<Film> topTenFilm(String count){
        ArrayList<Film> films = filmStorage.getFilms();
        Set<Film> collect = new HashSet<>();
        if (count == null) {
            films.stream()
                    .limit(10).
                    sorted(Comparator.comparingInt(Film::getCountLike).reversed())
                    .forEach(collect::add);

        } else {films.stream()
                .limit(Integer.parseInt(count))
                .sorted(Comparator.comparingInt(Film::getCountLike).reversed())
                .forEach(collect::add);
        }
        System.out.println(collect);
        //TODO удалить
        return collect;
    }



}
