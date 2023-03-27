package ru.tinkoff.edu.java.scrapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TgChatController {

    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<?> registerChat(@PathVariable("id") Long id) {
        // здесь должна быть реализация логики регистрации чата
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<?> deleteChat(@PathVariable("id") Long id) {
        // здесь должна быть реализация логики удаления чата
        return ResponseEntity.ok().build();
    }
}

