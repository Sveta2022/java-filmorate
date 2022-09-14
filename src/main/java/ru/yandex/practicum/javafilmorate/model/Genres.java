package ru.yandex.practicum.javafilmorate.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

//Класс описывает объект жанр
@Getter
@Setter
@AllArgsConstructor
@ToString
@SuperBuilder
@EqualsAndHashCode(of = "id")

public class Genres {
    @NotBlank
    private final int id;
    @NotBlank
    private final String name;

}
