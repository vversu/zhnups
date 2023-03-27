package ru.tinkoff.edu.java.bot;

import ru.tinkoff.edu.java.dto.ApiErrorResponse;
import ru.tinkoff.edu.java.dto.LinkUpdate;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/updates")
public class BotController {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void processUpdate(@RequestBody LinkUpdate updateRequest) {
        // В данном случае мы просто игнорируем полученные данные
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleException(Exception ex) {
        return new ApiErrorResponse(
                "Ошибка обработки запроса",
                "400",
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                Arrays.asList(ex.getStackTrace()).stream().map(StackTraceElement::toString).collect(Collectors.toList())
        );
    }
}