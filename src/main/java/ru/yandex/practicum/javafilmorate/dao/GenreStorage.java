package ru.yandex.practicum.javafilmorate.dao;

import ru.yandex.practicum.javafilmorate.model.Genres;

import java.util.List;
import java.util.Set;

public interface GenreStorage {

    //получить список MPA
    List<Genres> getAllGenre();

    //получить рейтинг по id
    Genres getGenreId(int id);
}
