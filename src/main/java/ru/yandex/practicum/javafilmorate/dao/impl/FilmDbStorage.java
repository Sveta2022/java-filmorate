package ru.yandex.practicum.javafilmorate.dao.impl;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.javafilmorate.dao.FilmStorage;
import ru.yandex.practicum.javafilmorate.dao.GenreStorage;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.Film;
import ru.yandex.practicum.javafilmorate.model.Genres;
import ru.yandex.practicum.javafilmorate.model.MpaRating;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@Repository
@Slf4j
@Primary
@RequiredArgsConstructor
public class FilmDbStorage implements FilmStorage {

    private final JdbcTemplate jdbcTemplate;
    GenreDbStorage genreStorage;

    private Film makeFilm(ResultSet rs, int rowNum) throws SQLException {
        Set<Genres> genres = new LinkedHashSet<>(getGenresByFilmID(rs.getLong("ID")));
        return new Film(rs.getLong("ID"),
                rs.getString("NAME"),
                rs.getString("DESCRIPTION"),
                rs.getDate("RELEASEDATE").toLocalDate(),
                rs.getInt("DURATION"),
                rs.getInt("RATE"),
                new MpaRating(rs.getInt("MPA.ID_MPA"), rs.getString("MPA.NAME")),
                genres);
    }

    @Override
    public Film create(Film film) {
        String sqlQuery = "insert into FILMS (NAME, DESCRIPTION,RELEASEDATE,DURATION, RATE, ID_MPA) " +
                "values (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sqlQuery, new String[]{"ID"});
            stmt.setString(1, film.getName());
            stmt.setString(2, film.getDescription());
            stmt.setDate(3, Date.valueOf(film.getReleaseDate()));
            stmt.setInt(4, film.getDuration());
            stmt.setInt(5, film.getRate());
            stmt.setInt(6, film.getMpa().getId());
            return stmt;
        }, keyHolder);
        film.setId(keyHolder.getKey().longValue());
        if (film.getGenres() != null) {
            downLoadGenres(film);
        }
        return film;
    }

    @Override
    public Film update(Film film) {
        String sqlQuery = "update FILMS set " +
                "NAME = ?, DESCRIPTION = ?, RELEASEDATE = ?, DURATION = ?, RATE = ?, ID_MPA = ?" +
                "where ID = ?";
        jdbcTemplate.update(sqlQuery
                , film.getName()
                , film.getDescription()
                , film.getReleaseDate()
                , film.getDuration()
                , film.getRate()
                , film.getMpa().getId(), film.getId());
        if (film.getGenres() != null && film.getGenres().size() > 0) {
            Set<Genres> genres = new LinkedHashSet<>();
            if (film.getGenres().size() > 0) {
                genres.addAll(film.getGenres());
            }
            film.setGenres(genres);
        }
        deleteGenres(film);
        downLoadGenres(film);
        return film;
    }

    private void deleteGenres(Film film) {
        String sqlQuery = "DELETE from GENRESFORONEFILM WHERE ID_FILM = ?";
        Long id = film.getId();
        jdbcTemplate.update(sqlQuery, id);
    }

    private void downLoadGenres(Film film) {
        Long id = film.getId();
        String sqlQuery = "INSERT INTO GENRESFORONEFILM (ID_FILM, ID_GENRES) values ( ?, ?)";
        if (film.getGenres() != null && film.getGenres().size() > 0) {
            for (Genres genre : film.getGenres()) {
                jdbcTemplate.update(sqlQuery, id, genre.getId());
            }
        }
    }

    @Override
    public List<Film> getFilms() {
        final String sqlQuery = "SELECT F.ID, F.NAME, F.DESCRIPTION, F.RELEASEDATE, F.DURATION, F.RATE, " +
                "M.ID_MPA, M.NAME FROM FILMS F " +
                "JOIN MPA M on M.ID_MPA = F.ID_MPA ";
        final List<Film> films = jdbcTemplate.query(sqlQuery, this::makeFilm);
        return films;
    }

    @Override
    public Film getFilmById(long id) {
        final String sqlQuery = "SELECT F.ID, F.NAME, F.DESCRIPTION, F.RELEASEDATE, F.DURATION, F.RATE, " +
                "M.ID_MPA, M.NAME FROM FILMS F " +
                "JOIN MPA M on M.ID_MPA = F.ID_MPA " +
                "WHERE F.ID = ?";
        final List<Film> films = jdbcTemplate.query(sqlQuery, this::makeFilm, id);
        if (films.size() != 1) {
            throw new NotFoundObjectException("Фильм с id " + id + " не найден");
        }
        return films.get(0);
    }

    public Set<Genres> getGenresByFilmID(Long filmId) {
        final String sqlQuery = "SELECT G2.ID_GENRE, NAME FROM GENRESFORONEFILM GF JOIN GENRES G2 on G2.ID_GENRE = GF.ID_GENRES " +
                "WHERE ID_FILM = ? ";
        List<Genres> genres = jdbcTemplate.query(sqlQuery, GenreDbStorage::makeGenre, filmId);
        return new HashSet<>(genres);
    }

    @Override
    public void addLike(long filmId, long userId) {
        String sqlQuery = "INSERT INTO FILMLIKES (ID_FILM, ID_USERS) values ( ?, ?) ";
        jdbcTemplate.update(sqlQuery, filmId, userId);
    }

    @Override
    public void deleteLike(long filmId, long userId) {
        String sqlQuery = "DELETE from FILMLIKES WHERE ID_FILM = ? AND ID_USERS = ? ";
        jdbcTemplate.update(sqlQuery, filmId, userId);
    }

    @Override
    public List<Film> topFilm(int count) {
        final String sqlQuery = "SELECT F.ID, F.NAME, F.DESCRIPTION, F.RELEASEDATE, F.DURATION, F.RATE, " +
                "M.ID_MPA, M.NAME FROM FILMS F " +
                "JOIN MPA M on M.ID_MPA = F.ID_MPA " +
                "ORDER BY F.RATE DESC LIMIT ?";
        final List<Film> films = jdbcTemplate.query(sqlQuery, this::makeFilm, count);
        return films;
    }
}
