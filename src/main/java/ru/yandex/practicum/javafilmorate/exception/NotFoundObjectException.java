package ru.yandex.practicum.javafilmorate.exception;


public class NotFoundObjectException extends NullPointerException {
    public NotFoundObjectException(String s) {
        super(s);
    }
}
