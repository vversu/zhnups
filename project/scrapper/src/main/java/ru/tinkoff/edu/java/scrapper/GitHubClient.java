package ru.tinkoff.edu.java.scrapper;

import reactor.core.publisher.Mono;
import ru.tinkoff.edu.java.scrapper.dto.RepositoryResponse;

public interface GitHubClient {

    Mono<RepositoryResponse> fetchRepository(String owner, String repo);
}

