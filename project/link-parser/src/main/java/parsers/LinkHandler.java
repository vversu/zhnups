package parsers;

import java.net.MalformedURLException;

public sealed interface LinkHandler permits GitHubLinkHandler, StackOverflowLinkHandler {

    /**
     * Проверяет, может ли обработчик обработать ссылку
     */
    boolean canHandle(String url);

    /**
     * Обрабатывает ссылку и возвращает результат
     */
    Object handle(String url) throws MalformedURLException;
}
