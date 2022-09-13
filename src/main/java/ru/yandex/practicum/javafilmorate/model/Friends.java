package ru.yandex.practicum.javafilmorate.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.yandex.practicum.javafilmorate.model.User;

//Класс описывает Объект дружбы между пользователями

@Getter
@Setter
@RequiredArgsConstructor
@SuperBuilder
@ToString
public class Friends {
    private Long id_users;
    private Long id_friends;
}
