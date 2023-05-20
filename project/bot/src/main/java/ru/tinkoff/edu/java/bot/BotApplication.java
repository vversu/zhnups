package ru.tinkoff.edu.java.bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;
import ru.tinkoff.edu.java.bot.telegram.MyTelegramBot;

@Configuration
@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class BotApplication {
	static String userRepo;
	public static void main(String[] args) {
	    var ctx = SpringApplication.run(BotApplication.class, args);
	    ApplicationConfig config = ctx.getBean(ApplicationConfig.class);
	    var bot = ctx.getBean(MyTelegramBot.class);
	    try {
	        bot.execute();
	    } catch (TelegramApiException e) {
	        e.printStackTrace();
	    }
	    System.out.println(userRepo);
        System.out.println(config);
    }
}
