package ru.yandex.practicum.javafilmorate.model;

import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    //должен быть положительным
    int id;
    //электронная почта не может быть пустой и должна содержать символ @
    String email;

    //логин не может быть пустым и содержать пробелы;
    String login;

    //имя для отображения может быть пустым — в таком случае будет использован логин;
    String name;

    // дата рождения не может быть в будущем.
    LocalDate birthday;
}
