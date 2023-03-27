package ru.tinkoff.edu.java.scrapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import ru.tinkoff.edu.java.dto.AddLinkRequest;
import ru.tinkoff.edu.java.dto.LinkResponse;
import ru.tinkoff.edu.java.dto.ListLinksResponse;
import ru.tinkoff.edu.java.dto.RemoveLinkRequest;

@RestController
@RequestMapping("/links")
public class LinksController {

    @GetMapping
    public ResponseEntity<ListLinksResponse> getAllLinks(@RequestHeader("Tg-Chat-Id") Long tgChatId) {
        // здесь должен быть код, который возвращает все отслеживаемые ссылки для заданного чата
        List<LinkResponse> links = new ArrayList<>();
        links.add(new LinkResponse("https://www.google.com", "Google"));
        links.add(new LinkResponse("https://www.github.com", "GitHub"));
        return ResponseEntity.ok(new ListLinksResponse(links));
    }

    @PostMapping
    public ResponseEntity<LinkResponse> addLink(@RequestHeader("Tg-Chat-Id") Long tgChatId, @RequestBody AddLinkRequest request) {
        // здесь должен быть код, который добавляет отслеживаемую ссылку для заданного чата
        LinkResponse linkResponse = new LinkResponse(request.url(), request.name());
        return ResponseEntity.ok(linkResponse);
    }

    @DeleteMapping
    public ResponseEntity<Void> removeLink(@RequestHeader("Tg-Chat-Id") Long tgChatId, @RequestBody RemoveLinkRequest request) {
        // здесь должен быть код, который удаляет отслеживаемую ссылку для заданного чата
        return ResponseEntity.ok().build();
    }
}
