package ru.yandex.practicum.javafilmorate.model;

import jdk.jshell.Snippet;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;


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
