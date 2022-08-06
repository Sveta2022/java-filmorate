package ru.yandex.practicum.javafilmorate.dao;

import ru.yandex.practicum.javafilmorate.model.Genre;

import java.util.Set;

public interface GenreStorage {

    //получить список MPA
    Set<Genre> getGenre();

    //получить рейтинг по id
    public Genre getGenreId(long id);
}
