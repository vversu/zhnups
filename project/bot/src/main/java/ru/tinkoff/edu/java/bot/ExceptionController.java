package ru.tinkoff.edu.java.bot;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import ru.tinkoff.edu.java.bot.dto.ApiErrorResponse;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleException(Exception ex) {
        return new ApiErrorResponse(
                "Ошибка сервера",
                "500",
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                Arrays.asList(ex.getStackTrace()).stream().map(StackTraceElement::toString).collect(Collectors.toList())
        );
    }
}

