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
@RestControllerAdvice(assignableTypes = {FilmController.class, UserController.class})
@Slf4j
public class ErrorHandler {
    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleValidation(final ValidationException v){
        log.warn(v.getMessage());
        return new ResponseEntity<>(
                Map.of("Validation is not correct ", v.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleNotFound(final NotFoundObjectException n){
        log.warn(n.getMessage());
        return new ResponseEntity<>(
                Map.of("Object is not found ", n.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handleAllOther(final AllOtherException a){
        log.warn(a.getMessage());
        return new ResponseEntity<>(
                Map.of("Please, attention ", a.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
