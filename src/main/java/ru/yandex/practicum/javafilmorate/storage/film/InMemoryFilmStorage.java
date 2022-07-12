package ru.yandex.practicum.javafilmorate.storage.film;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.javafilmorate.model.Film;

import java.util.ArrayList;

@Component
@Slf4j
@Getter
@Setter
public class InMemoryFilmStorage implements FilmStorage {

    @Override
    public Film create(Film film) {
        films.put(film.getId(), film);
        return film;
    }
    @Override
    public Film update(Film film) {
        films.put(film.getId(), film);
        return film;
    }

    @Override
    public ArrayList <Film> getFilms() {
        return new ArrayList<>(films.values());
    }
}
