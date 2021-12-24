package ua.goit.moneybot.controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.goit.moneybot.view.ConsoleView;
import ua.goit.moneybot.view.ConsoleViewImpl;

import java.util.Optional;

public class Controller extends TelegramLongPollingBot {
    ConsoleViewImpl view = new ConsoleView();
    Keyboards keyboards = new Keyboards();

    @Override
    public String getBotUsername() {
        return "@goittestingbot_bot";
    }    //есл хотите тестить сюда вбиваете свои значения

    @Override
    public String getBotToken() {
        return "5003580560:AAE9p7Ohh5rFGGTomwmqFBqp5H35nm0NySs";  // сюда свой токен
    }


    @Override
    public void onUpdateReceived(Update update) {         //мониторит ввод в чат (наблюдатель)
        if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());
        } else if (update.hasMessage()) {
            try {
                handleMessage(update.getMessage());
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleCallback(CallbackQuery callbackQuery) {            //отвечает на колбеки
        Message message = callbackQuery.getMessage();
        try {
            if (callbackQuery.getData().equals("get_info")) {
                execute(SendMessage.builder()
                        .text("текущие курсы")
                        .chatId(message.getChatId().toString())
                        .build());
            } else if (callbackQuery.getData().equals("settings")) {
                execute(SendMessage.builder()
                        .text("Настройки")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getSettingsMenu()).build())
                        .build());
            } else if (callbackQuery.getData().equals("digitAfterComa")) {
                execute(SendMessage.builder()
                        .text("Выберите количество знаков после запятой")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getDigitMenu()).build())
                        .build());
            } else if (callbackQuery.getData().equals("banks")) {
                execute(SendMessage.builder()
                        .text("Выберите желаемый банк. Только один")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getBankMenu()).build())
                        .build());
            } else if (callbackQuery.getData().equals("currency")) {
                execute(SendMessage.builder()
                        .text("Выберите нужные курсы, можно несколько")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getCurrencyMenu()).build())
                        .build());
            } else if (callbackQuery.getData().equals("/markup")) {
                execute(SendMessage.builder()
                        .text("Выберите время оповещения")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(ReplyKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert()).build())
                        .build());
            }

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    private void handleMessage(Message message) throws TelegramApiException {
        //handle command
        if (message.hasText() && message.hasEntities()) {
            Optional<MessageEntity> commandEntity = message.getEntities().stream().filter(e -> "bot_command".equals(e.getType())).findFirst();
            if (commandEntity.isPresent()) {
                String command = message.getText().substring(commandEntity.get().getOffset(), commandEntity.get().getLength());
                if (command.equals("/start")) {

                    execute(SendMessage.builder()
                            .text("Добро пожаловать. Этот бот поможет отслеживать актуальные курсы валют")
                            .chatId(message.getChatId().toString())
                            .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getMainMenu()).build())
                            .build());


                }
            }
        }
    }


}
