package ru.tinkoff.edu.java.scrapper;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.tinkoff.edu.java.scrapper.dto.AddLinkRequest;
import ru.tinkoff.edu.java.scrapper.dto.ApiErrorResponse;
import ru.tinkoff.edu.java.scrapper.dto.LinkResponse;
import ru.tinkoff.edu.java.scrapper.dto.ListLinksResponse;
import ru.tinkoff.edu.java.scrapper.dto.RemoveLinkRequest;

@RestController
@RequestMapping("/api")
public class ApiController {

    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<Void> registerChat(@PathVariable Long id) {
        // Заглушка - просто возвращаем успешный статус
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<Void> deleteChat(@PathVariable Long id) {
        // Заглушка - просто возвращаем успешный статус
        return ResponseEntity.ok().build();
    }

    @GetMapping("/links")
    public ResponseEntity<ListLinksResponse> getAllLinks(@RequestHeader("Tg-Chat-Id") Long chatId) {
        // Заглушка - возвращаем пустой список ссылок
        return ResponseEntity.ok(new ListLinksResponse(new ArrayList<>()));
    }

    @PostMapping("/links")
    public ResponseEntity<LinkResponse> addLink(@RequestHeader("Tg-Chat-Id") Long chatId, @RequestBody AddLinkRequest request) {
        // Заглушка - возвращаем ссылку с фиктивным ID
        return ResponseEntity.ok(new LinkResponse("1", request.url()));
    }

    @DeleteMapping("/links")
    public ResponseEntity<Void> removeLink(@RequestHeader("Tg-Chat-Id") Long chatId, @RequestBody RemoveLinkRequest request) {
        // Заглушка - просто возвращаем успешный статус
        return ResponseEntity.ok().build();
    }

    @RestControllerAdvice
    public static class ExceptionHandler {

        @org.springframework.web.bind.annotation.ExceptionHandler
        public ResponseEntity<ApiErrorResponse> handleException(Exception ex) {
            // Обработка исключений - возвращаем 400-й статус с сообщением об ошибке
            return ResponseEntity.badRequest().body(new ApiErrorResponse(ex.getMessage()));
        }
    }
}