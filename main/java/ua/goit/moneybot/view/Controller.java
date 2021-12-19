package ua.goit.moneybot.view;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Controller extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "@goittestingbot_bot";
    }

    @Override
    public String getBotToken() {
        return "5003580560:AAE9p7Ohh5rFGGTomwmqFBqp5H35nm0NySs";
    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleMessage(Message message) throws TelegramApiException {
        //handle command
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                switch (command) {
                    case "/start":
                    List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
                    buttons.add(
                            Arrays.asList(
                                    InlineKeyboardButton.builder()
                                            .text("Получить инфо")
                                            .callbackData("1")
                                            .build()
                    ));
                        buttons.add(
                                Arrays.asList(
                                        InlineKeyboardButton.builder()
                                                .text("Настройки")
                                                .callbackData("1")
                                                .build()
                                ));

                        execute(SendMessage.builder()
                                .text("Добро пожаловать. Этот бот поможет отслеживать актуальные курсы валют")
                                .chatId(message.getChatId().toString())
                                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                                .build());
                }
            }
        }
    }

    public static void main(String[] args) throws TelegramApiException {
        Controller bot = new Controller();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(bot);
    }


}
