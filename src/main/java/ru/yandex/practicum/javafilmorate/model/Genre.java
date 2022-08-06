package ru.yandex.practicum.javafilmorate.model;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Genre {
    private final int id;
    private final String name;
}
