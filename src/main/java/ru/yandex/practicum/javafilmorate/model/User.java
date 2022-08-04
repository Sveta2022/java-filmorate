package ru.yandex.practicum.javafilmorate.model;

import lombok.*;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//Класс описывает пользователя

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    //должен быть положительным
    private long id;
    //электронная почта не может быть пустой и должна содержать символ @
    private String email;
    //логин не может быть пустым и содержать пробелы;
    private String login;
    //имя для отображения может быть пустым — в таком случае будет использован логин;
    private String name;
    // дата рождения не может быть в будущем.
    private LocalDate birthday;
    // список друзей пользователя по их id
   // private Set<Long> friends = new HashSet<>();


}
