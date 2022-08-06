package ru.yandex.practicum.javafilmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.javafilmorate.dao.GenreStorage;
import ru.yandex.practicum.javafilmorate.model.Genre;
import ru.yandex.practicum.javafilmorate.model.MpaRating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class GenreDbStorage implements GenreStorage {

    private final JdbcTemplate jdbcTemplate;

    static Genre makeGenre(ResultSet rs, int rowNum) throws SQLException {
        return new Genre(rs.getInt("ID_GENRE"),
                rs.getString("NAME"));
    }

    @Override
    public Set<Genre> getGenre() {
        final String sqlQuery = "SELECT ID_GENRE, NAME FROM GENRES " +
                "WHERE ID_GENRE = ?";
        return (Set<Genre>) jdbcTemplate.query(sqlQuery,GenreDbStorage::makeGenre);
    }

    @Override
    public Genre getGenreId(long id) {
        return null;
    }
}
