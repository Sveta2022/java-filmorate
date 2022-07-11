package ru.yandex.practicum.javafilmorate.storage.film;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.javafilmorate.model.Film;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public interface FilmStorage {

    Map<Long, Film> films = new HashMap<>();

    Film create(Film film);


    Film update(Film film);


    ArrayList getFilms();
}
