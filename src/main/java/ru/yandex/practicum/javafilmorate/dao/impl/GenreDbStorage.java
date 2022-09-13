package ru.yandex.practicum.javafilmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.javafilmorate.dao.GenreStorage;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.Genres;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class GenreDbStorage implements GenreStorage {

    private final JdbcTemplate jdbcTemplate;

    static Genres makeGenre(ResultSet rs, int rowNum) throws SQLException {
        return Genres.builder()
                .id(rs.getInt("ID_GENRE"))
                .name(rs.getString("NAME"))
                .build();
    }

    @Override
    public List<Genres> getAllGenre() {
        final String sqlQuery = "SELECT ID_GENRE, NAME FROM GENRES ";
        List<Genres> genres = jdbcTemplate.query(sqlQuery, GenreDbStorage::makeGenre);
        return genres;
    }

    @Override
    public Genres getGenreId(int id) {
        final String sqlQuery = "SELECT ID_GENRE, NAME FROM GENRES " +
                "WHERE ID_GENRE = ?";
        List<Genres> genres = jdbcTemplate.query(sqlQuery, GenreDbStorage::makeGenre, id);
        if(genres.size()!=1){
            throw new NotFoundObjectException("Жанра с id " + id + " нет в списке жанров");
        }
        return genres.get(0);
    }
}
