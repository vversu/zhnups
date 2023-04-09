package ru.tinkoff.edu.java.scrapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {
    @Bean
    public GitHubClient gitHubClient(WebClient.Builder webClientBuilder) {
        return new GitHubWebClient(webClientBuilder);
    }
    private final String stackOverflowBaseUrl = "https://api.stackexchange.com/2.3/";

    @Bean
    public WebClient stackOverflowWebClient() {
        return WebClient.builder()
                .baseUrl(stackOverflowBaseUrl)
                .build();
    }

    @Bean
    public StackOverflowClient stackOverflowClient(WebClient stackOverflowWebClient) {
        return new StackOverflowWebClient(stackOverflowBaseUrl);
    }

}