package ru.yandex.practicum.javafilmorate.dao.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.javafilmorate.dao.FilmStorage;
import ru.yandex.practicum.javafilmorate.model.Film;

import java.util.ArrayList;

@Repository
@Slf4j
@Primary
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;

 @Autowired
    public FilmDbStorage(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Film create(Film film) {
        return null;
    }

    @Override
    public Film update(Film film) {
        return null;
    }

    @Override
    public ArrayList<Film> getFilms() {
        return null;
    }

    @Override
    public Film getFilmById(long id) {
        return null;
    }
}
