package ru.yandex.practicum.javafilmorate.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

//Класс описывает фильм

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

}
