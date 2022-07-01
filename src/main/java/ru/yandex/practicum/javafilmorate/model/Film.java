package ru.yandex.practicum.javafilmorate.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Film {

    private int id;
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

}
