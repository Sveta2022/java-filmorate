package ru.yandex.practicum.javafilmorate.exception;

/*
Класс передает сообщение для всех ситуаций, если искомый объект не найден;
 */
public class NotFoundObjectException extends NullPointerException {
    public NotFoundObjectException(String s) {
        super(s);
    }
}
