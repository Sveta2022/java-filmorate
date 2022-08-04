package ru.yandex.practicum.javafilmorate.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//Класс описывает фильм

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    // "один пользователь — один лайк". Массив хранит id пользователя
    private Set<Long> likes = new HashSet<>();

    // private int genre_Id;
   // private int rating_id;

    public Film(long id, String name, String description, LocalDate releaseDate, int duration
                //int genre_Id, int rating_id
                ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.releaseDate = releaseDate;
        this.duration = duration;
    //    this.genre_Id = genre_Id;
     //   this.rating_id = rating_id;
    }

    public int getCountLike() {
        return likes.size();
    }


}
