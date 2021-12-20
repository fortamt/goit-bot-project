package ua.goit.moneybot.controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.goit.moneybot.model.BankResponse;
import ua.goit.moneybot.view.ConsoleView;
import ua.goit.moneybot.view.ConsoleViewImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Controller extends TelegramLongPollingBot {
    BankResponse bankResponse = new BankResponse();
    ConsoleViewImpl view = new ConsoleView();

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
        if(update.hasCallbackQuery()){
            handleCallback(update.getCallbackQuery());
        } else if (update.hasMessage()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleCallback(CallbackQuery callbackQuery) {
        Message message = callbackQuery.getMessage();
        try {
            execute(SendMessage.builder()
                    .text(view.getUsdSell(bankResponse))
                    .chatId(message.getChatId().toString())
                    .build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void handleMessage(Message message) throws TelegramApiException{
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
                                            .callbackData("get_info")
                                            .build()));
                    buttons.add(
                            Arrays.asList(
                                    InlineKeyboardButton.builder()
                                            .text("Настройки")
                                            .callbackData("settings")
                                            .build()));

                        execute(SendMessage.builder()
                                .text("Добро пожаловать. Этот бот поможет отслеживать актуальные курсы валют")
                                .chatId(message.getChatId().toString())
                                .replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
                                .build());


                }
            }
        }
    }




}
