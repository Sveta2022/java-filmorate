package ru.yandex.practicum.javafilmorate.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.javafilmorate.dao.MpaStorage;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.MpaRating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class MpaDbStorage implements MpaStorage {
    private final JdbcTemplate jdbcTemplate;

   private static MpaRating makeMpa(ResultSet rs, int rowNum) throws SQLException {
        return MpaRating.builder()
                .id(rs.getInt("ID_MPA"))
                .name(rs.getString("NAME"))
                .build();
    }

    @Override
    public List<MpaRating> getMpas() {
        String sqlQuery = "SELECT ID_MPA, NAME FROM MPA ";
        return jdbcTemplate.query(sqlQuery, MpaDbStorage::makeMpa);
    }

    @Override
    public List<MpaRating> getMpaRatingId(int id) {
        String sqlQuery = "SELECT ID_MPA, NAME FROM MPA " +
                "WHERE ID_MPA = ?";
        List<MpaRating> mpas = jdbcTemplate.query(sqlQuery, MpaDbStorage::makeMpa, id);
        if(mpas.size()!=1){
            throw new NotFoundObjectException("MPA с id " + id + " нет в списке");
        }
        return mpas;
    }
}


