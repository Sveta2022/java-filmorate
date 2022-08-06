package ru.yandex.practicum.javafilmorate.dao;

import ru.yandex.practicum.javafilmorate.model.MpaRating;

import java.util.List;
import java.util.Set;

public interface MpaStorage {

    //получить список MPA
    List<MpaRating> getMpas();

    //получить рейтинг по id
    public List<MpaRating> getMpaRatingId(int id);
}
