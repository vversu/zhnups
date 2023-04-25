package ru.tinkoff.edu.java.bot.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.BotCommand;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SetMyCommands;

import ru.tinkoff.edu.java.bot.configuration.ApplicationConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class TelegramBotService implements CommandExecutor {
    public TelegramBot bot;
    public final Map<Integer, User> users = new HashMap<>();
    
    @Autowired
    public TelegramBotService(ApplicationConfig config) {
        bot = new TelegramBot(config.telegramBotToken());
        List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand("start", "Зарегистрировать пользователя"));
        commands.add(new BotCommand("help", "Показать доступные команды"));
        commands.add(new BotCommand("track", "Начните отслеживать ссылку"));
        commands.add(new BotCommand("untrack", "Прекратить отслеживание ссылки"));
        commands.add(new BotCommand("list", "Список отслеживаемых ссылок"));
        bot.execute(new SetMyCommands(commands.toArray(new BotCommand[0])));
    }


    @Override
    public void start(int userId) {
        User user = users.getOrDefault(userId, new User(userId, null));
        registerUser(user);
    }

    @Override
    public void help(int userId) {
        sendHelpMessage(userId);
    }

    @Override
    public void track(int userId, String link) {
        User user = users.getOrDefault(userId, new User(userId, null));
        trackLink(user, link);
    }

    @Override
    public void untrack(int userId, String link) {
        User user = users.getOrDefault(userId, new User(userId, null));
        untrackLink(user, link);
    }

    @Override
    public void list(int userId) {
        User user = users.getOrDefault(userId, new User(userId, null));
        listTrackedLinks(user);
    }

    private void registerUser(User user) {
        users.putIfAbsent(user.userId(), user);
        bot.execute(new SendMessage(user.userId(), "Здравствуйте!"));
    }   
    
    public void processCommand(Message message) {
        String text = message.getText();
        if (text.startsWith("/start")) {
            start(message.getFrom().getId());
        } else if (text.startsWith("/help")) {
            help(message.getFrom().getId());
        } else if (text.startsWith("/track")) {
            String link = text.substring("/track".length()).trim();
            if (link.isEmpty()) {
                sendEmptyLinkMessage(message.getFrom().getId());
            } else {
                track(message.getFrom().getId(), link);
            }
        } else if (text.startsWith("/untrack")) {
            String link = text.substring("/untrack".length()).trim();
            untrack(message.getFrom().getId(), link);
        } else if (text.startsWith("/list")) {
            list(message.getFrom().getId());
        } else {
            sendUnknownCommandMessage(message.getFrom().getId());
        }
    }

    private void sendEmptyLinkMessage(int userId) {
        bot.execute(new SendMessage(userId, "Ошибка: не указана ссылка для отслеживания. Используйте команду в формате: /track <ссылка>."));
    }

    
    private void sendHelpMessage(int userId) {
        bot.execute(new SendMessage(userId, "Доступные команды:\n" +
                "/start - Зарегистрировать пользователя\n" +
                "/help - Показать доступные команды\n" +
                "/track - Начните отслеживать ссылку\n" +
                "/untrack - Прекратить отслеживание ссылки\n" +
                "/list - Список отслеживаемых ссылок"));
    }

    private void trackLink(User user, String link) {
        user.addTrackedLink(link);
        bot.execute(new SendMessage(user.userId(), "Ссылка добавлена в список отслеживания: " + link));
    }

    private void untrackLink(User user, String link) {
        if (user.removeTrackedLink(link)) {
            bot.execute(new SendMessage(user.userId(), "Ссылка удалена из списка отслеживания: " + link));
        } else {
            bot.execute(new SendMessage(user.userId(), "Ссылка не найдена в списке отслеживания: " + link));
        }
    }

    private void listTrackedLinks(User user) {
        Set<String> trackedLinks = user.getTrackedLinks();
        if (trackedLinks.isEmpty()) {
            bot.execute(new SendMessage(user.userId(), "В настоящее время никакие ссылки не отслеживаются."));
        } else {
            StringBuilder messageBuilder = new StringBuilder("Отслеживаемые ссылки: \n");
            trackedLinks.forEach(link -> messageBuilder.append("- ").append(link).append("\n"));
            bot.execute(new SendMessage(user.userId(), messageBuilder.toString()));
        }
    }

    
    
    private void sendUnknownCommandMessage(int userId) {
        bot.execute(new SendMessage(userId, "Неизвестная команда."));
    }
}
