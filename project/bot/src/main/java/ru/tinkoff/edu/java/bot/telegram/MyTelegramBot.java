package ru.tinkoff.edu.java.bot.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class MyTelegramBot extends TelegramLongPollingBot {
	  private final TelegramBotService commandProcessor;

	    @Autowired
	    public MyTelegramBot(TelegramBotService commandProcessor) {
	        this.commandProcessor = commandProcessor;
	    }

	    @Override
	    public void onUpdateReceived(Update update) {
	        if (update.hasMessage() && update.getMessage().hasText()) {
	            Message message = update.getMessage();
	            commandProcessor.processCommand(message);
	        }
	    }

    @Override
    public String getBotUsername() {
        return "@zhnups_bot";
    }

    @Override
    public String getBotToken() {
        return "6167977115:AAERKk1oONm2sgdYpf9R0Z6nwxGQkp3PZ8Y";
    }
    
    public void execute() throws TelegramApiException {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiRequestException e) {
            throw new TelegramApiException("Ошибка", e);
        }
    }

}
