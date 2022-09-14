package ru.yandex.practicum.javafilmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.javafilmorate.dao.GenreStorage;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.Genres;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class GenresService {
    private final GenreStorage genreStorage;

    // получить список всех жанров
    public List<Genres> getAllGenres() {
        return genreStorage.getAllGenre();
    }

    // получить жанр по id
    public Genres getGenreById(int id) {

        return genreStorage.getGenreId(id);
    }
}
