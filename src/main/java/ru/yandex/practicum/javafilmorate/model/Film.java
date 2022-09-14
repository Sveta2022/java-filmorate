package ru.yandex.practicum.javafilmorate.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

//Класс описывает фильм

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString

public class Film {

    private long id;
    //название не может быть пустым;
    @NotBlank
    private String name;
    //максимальная длина описания — 200 символов;
    @Length(max = 200)
    private String description;
    // дата релиза — не раньше 28 декабря 1895 года;
    private LocalDate releaseDate;
    //продолжительность фильма должна быть положительной.
    @Positive
    private int duration;
    //Эта оценка для фильма
    private Integer rate = 0;
    //Эта оценка определяет возрастное ограничение для фильма
    private MpaRating mpa;
    //У фильма может быть сразу несколько жанров, а у поля — несколько значений.
    private Set<Genres> genres;
}
