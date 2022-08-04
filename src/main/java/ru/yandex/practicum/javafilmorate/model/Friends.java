package ru.yandex.practicum.javafilmorate.model;

import lombok.*;
import ru.yandex.practicum.javafilmorate.model.User;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Friends {
    User user;
    User friends;
}
