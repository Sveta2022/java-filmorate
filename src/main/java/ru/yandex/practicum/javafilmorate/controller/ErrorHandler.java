package ru.yandex.practicum.javafilmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.javafilmorate.exception.AllOtherException;
import ru.yandex.practicum.javafilmorate.exception.NotFoundObjectException;
import ru.yandex.practicum.javafilmorate.exception.ValidationException;

import java.util.Map;

/*
Класс содержит методы обработки исключений
 */
@RestControllerAdvice(assignableTypes = {FilmController.class, UserController.class, MpaController.class, GenreController.class})
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleValidation(final ValidationException v) {
        log.warn("400 {}", v.getMessage(), v);
        return new ResponseEntity<>(
                Map.of("Validation is not correct ", v.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleNotFound(final NotFoundObjectException n) {
        log.warn("404 {}", n.getMessage(), n);
        return new ResponseEntity<>(
                Map.of("Object is not found ", n.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleAllOther(final AllOtherException a) {
        log.warn("500 {}", a.getMessage(), a);
        return new ResponseEntity<>(
                Map.of("Please, attention ", a.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
