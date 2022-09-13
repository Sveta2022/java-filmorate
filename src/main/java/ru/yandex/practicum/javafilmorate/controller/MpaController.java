package ru.yandex.practicum.javafilmorate.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.MpaRating;
import ru.yandex.practicum.javafilmorate.service.MpaService;

import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/mpa")
public class MpaController {

    final private MpaService mpaService;

    //    получить список MPA
    @GetMapping
    public List<MpaRating> getAllUsers() {
        log.info("Получен запрос на получение списка всех MPA");
        return mpaService.getAllMpas();
    }

    //    получить MPA по id
    @GetMapping("/{id}")
    public MpaRating getMpaById(@PathVariable int id) {
        log.info("Получен запрос на поиск mpa с id: " + id);
        Optional<MpaRating> mpa = Optional.of(mpaService.getMpaById(id).get(0));
        if (mpa.isPresent()) {
            return mpaService.getMpaById(id).get(0);
        }
        throw new NotFoundObjectException("список MPA не найден");
    }
}
