package ru.yandex.practicum.javafilmorate.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.Genres;
import ru.yandex.practicum.javafilmorate.model.MpaRating;
import ru.yandex.practicum.javafilmorate.service.GenresService;


import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/genres")
public class GenreController {

    final private GenresService genresService;

    //    получить список MPA
    @GetMapping
    public List<Genres> getAllGenres() {
        log.info("Получен запрос на получение списка всех жанров");
        return genresService.getAllGenres();
    }

    //    получить MPA по id
    @GetMapping("/{id}")
    public Genres getGenreById(@PathVariable int id) {
        log.info("Получен запрос на поиск жанра с id: " + id);
        Optional<Genres> genre = Optional.of(genresService.getGenreById(id));
        if (genre.isPresent()) {
            return genresService.getGenreById(id);
        }
        throw new NotFoundObjectException("список Жанров не найден");
    }
}
