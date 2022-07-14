package ru.yandex.practicum.javafilmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.javafilmorate.exception.AllOtherException;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;

import java.util.Map;

/*
Класс содержит методы обработки исключений
 */
@RestControllerAdvice(assignableTypes = {FilmController.class, UserController.class})
@Slf4j
public class ErrorHandler {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidation(final ValidationException v) {
        log.warn(v.getMessage());
        return Map.of("Validation is not correct ", v.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> handleNotFound(final NotFoundObjectException n) {
        log.warn(n.getMessage());
        return Map.of("Object is not found ", n.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, String> handleAllOther(final AllOtherException a) {
        log.warn(a.getMessage());
        return Map.of("Please, attention ", a.getMessage());
    }
}
