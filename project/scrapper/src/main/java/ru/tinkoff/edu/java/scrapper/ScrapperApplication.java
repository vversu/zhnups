package ru.tinkoff.edu.java.scrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.tinkoff.edu.java.scrapper.configuration.ApplicationProperties;
import ru.tinkoff.edu.java.scrapper.configuration.GitHubConfiguration;
import ru.tinkoff.edu.java.scrapper.configuration.StackOverflowConfiguration;


@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({
        ApplicationProperties.class,
        GitHubConfiguration.class,
        StackOverflowConfiguration.class}
)
public class ScrapperApplication {
    public static void main(String[] args) {
        var ctx = SpringApplication.run(ScrapperApplication.class, args);
        ApplicationProperties config = ctx.getBean(ApplicationProperties.class);
        System.out.println(config);
    }
}