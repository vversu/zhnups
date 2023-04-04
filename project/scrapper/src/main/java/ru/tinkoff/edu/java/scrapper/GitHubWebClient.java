package ru.tinkoff.edu.java.scrapper;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.RepositoryResponse;

@Component
public class GitHubWebClient implements GitHubClient {

    private final WebClient webClient;

    public GitHubWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.github.com").build();
    }

    @Override
    public Mono<RepositoryResponse> fetchRepository(String owner, String repo) {
        return webClient.get()
                .uri("/repos/{owner}/{repo}", owner, repo)
                .retrieve()
                .bodyToMono(RepositoryResponse.class);
    }
}
