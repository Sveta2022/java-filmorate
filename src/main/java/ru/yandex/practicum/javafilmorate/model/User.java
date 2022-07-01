package ru.yandex.practicum.javafilmorate.model;

import lombok.*;

import java.time.LocalDate;

//Класс описывает пользователя

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    //должен быть положительным
    private int id;
    //электронная почта не может быть пустой и должна содержать символ @
    private String email;
    //логин не может быть пустым и содержать пробелы;
    private String login;
    //имя для отображения может быть пустым — в таком случае будет использован логин;
    private String name;
    // дата рождения не может быть в будущем.
    private LocalDate birthday;
}
