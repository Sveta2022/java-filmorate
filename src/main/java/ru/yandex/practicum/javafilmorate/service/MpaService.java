package ru.yandex.practicum.javafilmorate.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.javafilmorate.dao.MpaStorage;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.model.MpaRating;


import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class MpaService {
    private final MpaStorage mpaStorage;

    public List<MpaRating> getMpaById(int id) {
        return mpaStorage.getMpaRatingId(id);
    }

    //получить список всех MPA
    public List<MpaRating> getAllMpas() {
        return mpaStorage.getMpas();
    }

}
