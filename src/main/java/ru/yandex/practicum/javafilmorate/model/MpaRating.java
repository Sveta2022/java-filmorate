package ru.yandex.practicum.javafilmorate.model;

import jdk.jshell.Snippet;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
//Класс описывает объект MPA рейтинг

@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
@ToString
public class MpaRating {
    @NotBlank
    private final int id;
    @NotBlank
    private final String name;

}
