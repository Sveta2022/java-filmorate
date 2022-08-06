package ru.yandex.practicum.javafilmorate.dao.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.javafilmorate.dao.FilmStorage;
import ru.yandex.practicum.javafilmorate.model.Film;
import ru.yandex.practicum.javafilmorate.model.Genre;
import ru.yandex.practicum.javafilmorate.model.MpaRating;
import ru.yandex.practicum.javafilmorate.model.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@Slf4j
@Primary
@RequiredArgsConstructor
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;

    static Film makeFilm(ResultSet rs, int rowNum) throws SQLException {
        return new Film(rs.getLong("ID"),
                rs.getString("NAME"),
                rs.getString("DESCRIPTION"),
                rs.getDate("RELEASEDATE").toLocalDate(),
                rs.getInt("DURATION"),
                new MpaRating(rs.getInt("MPA.ID_MPA"), rs.getString("MPA.NAME")),
                Set.of(new Genre(rs.getInt("GENRE.ID_GENRE"), rs.getString("GENRE.NAME")))
        );
    }

    @Override
    public Film create(Film film) {
        String sqlQuery = "insert into FILMS (NAME, DESCRIPTION,RELEASEDATE,DURATION, ID_MPA) values (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"ID"});
            stmt.setString(1, film.getName());
            stmt.setString(2, film.getDescription());
            stmt.setDate(3, Date.valueOf(film.getReleaseDate()));
            stmt.setInt(4, film.getDuration());
            stmt.setInt(5, film.getMpa().getId());
            return stmt;
        }, keyHolder);
        film.setId(keyHolder.getKey().longValue());
        return film;
    }

    @Override
    public Film update(Film film) {
        String sqlQuery = "update FILMS set " +
                "NAME = ?, DESCRIPTION = ?, RELEASEDATE = ?, DURATION = ?, ID_MPA = ?" +
                "where ID = ?";
        jdbcTemplate.update(sqlQuery
                , film.getName()
                , film.getDescription()
                , film.getReleaseDate()
                , film.getDuration()
                , film.getMpa().getId());

        return film;
    }

    @Override
    public ArrayList<Film> getFilms() {
        final String sqlQuery = "SELECT ID, NAME, DESCRIPTION, RELEASEDATE, DURATION, ID_MPA FROM FILMS " +
                "WHERE ID = ?";
        final ArrayList<Film> films = (ArrayList<Film>) jdbcTemplate.query(sqlQuery, FilmDbStorage::makeFilm);
        return films;
    }

    @Override
    public Film getFilmById(long id) {

        final String sqlQuery = "SELECT ID, NAME, DESCRIPTION, RELEASEDATE, DURATION, ID_MPA FROM FILMS " +
                "WHERE ID = ?";
        final List<Film> films = jdbcTemplate.query(sqlQuery, FilmDbStorage::makeFilm, id);
        if (films.size() != 1) {
            return null;
        }
        return films.get(0);
    }
}
