package ru.yandex.practicum.javafilmorate.dao.rowmapper;

import org.springframework.jdbc.core.RowMapper;
import ru.yandex.practicum.javafilmorate.model.MpaRating;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MpaRowMapper implements RowMapper <MpaRating> {
    @Override
    public MpaRating mapRow(ResultSet rs, int rowNum) throws SQLException {
        MpaRating mpa = new MpaRating(rs.getInt("ID_MPA"), rs.getString("NAME"));
    return mpa;
    }
}
