package ru.tinkoff.edu.java.scrapper;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.QuestionResponse;

@Component
public class StackOverflowWebClient implements StackOverflowClient {

    private final WebClient webClient;

    public StackOverflowWebClient(String baseUrl) {
        this.webClient = WebClient.builder().baseUrl(baseUrl).build();
    }

    @Override
    public Mono<QuestionResponse> fetchQuestion(long questionId) {
        return webClient.get()
                .uri("/questions/{questionId}?site=stackoverflow", questionId)
                .retrieve()
                .bodyToMono(QuestionResponse.class);
    }
}
