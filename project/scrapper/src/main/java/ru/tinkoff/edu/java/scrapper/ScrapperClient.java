package ru.tinkoff.edu.java.scrapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.net.URI;

import javax.naming.directory.SearchResult;

@Component
public class ScrapperClient {

    private final WebClient webClient;

    public ScrapperClient(@Value("${scrapper.base-url}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public Mono<SearchResult> search(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("query", query)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(SearchResult.class);
    }

    public Mono<Page> getPage(URI url) {
        return webClient.get()
                .uri(url)
                .accept(MediaType.TEXT_HTML)
                .retrieve()
                .bodyToMono(Page.class);
    }

    public Mono<Page> getHomePage() {
        return webClient.get()
                .uri("/")
                .accept(MediaType.TEXT_HTML)
                .retrieve()
                .bodyToMono(Page.class);
    }

}
