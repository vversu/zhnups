package ru.tinkoff.edu.java.scrapper;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.QuestionResponse;

public interface StackOverflowClient {

    Mono<QuestionResponse> fetchQuestion(long questionId);
}
