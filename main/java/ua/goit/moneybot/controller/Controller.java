package ua.goit.moneybot.controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.goit.moneybot.model.UserService;

import java.util.Optional;

public class Controller extends TelegramLongPollingBot {
    Keyboards keyboards = new Keyboards();
    UserService userService = UserService.create();



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

    private void handleCallback(CallbackQuery callbackQuery) {            //отвечает на колбеки
        Message message = callbackQuery.getMessage();
        try {
            if(callbackQuery.getData().equals("get_info")) {
                execute(SendMessage.builder()
                        .text(userService.getInfo(message))
                        .chatId(message.getChatId().toString())
                        .build());
                execute(SendMessage.builder()
                        .text("Добро пожаловать. Этот бот поможет отслеживать актуальные курсы валют")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getMainMenu()).build())
                        .build());
            }
            if(callbackQuery.getData().equals("settings")){
                execute(SendMessage.builder()
                        .text("Настройки")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getSettingsMenu()).build())
                        .build());
            }
            if(callbackQuery.getData().equals("digitAfterComa")){
                execute(SendMessage.builder()
                        .text("Выберите количество знаков после запятой")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getDigitMenu()).build())
                        .build());
            }
            if(callbackQuery.getData().equals("banks")){
                execute(SendMessage.builder()
                        .text("Выберите желаемый банк. Только один")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getBankMenu()).build())
                        .build());
            }
            if(callbackQuery.getData().equals("currency")){
                execute(SendMessage.builder()
                        .text("Выберите нужные курсы, можно несколько")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getCurrencyMenu(message)).build())
                        .build());
            }
            if(callbackQuery.getData().equals("USD")){
                userService.changeCurrencyUSD(message);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getCurrencyMenu(message)).build())
                        .build());
            }
            if(callbackQuery.getData().equals("EUR")){
                userService.changeCurrencyEUR(message);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getCurrencyMenu(message)).build())
                        .build());
            }
            if(callbackQuery.getData().equals("RUB")){
                userService.changeCurrencyRUB(message);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getCurrencyMenu(message)).build())
                        .build());
            }
            if(callbackQuery.getData().equals("/2")){
                userService.changeRounding(message, (byte) 2);
            }
            if(callbackQuery.getData().equals("/3")){
                userService.changeRounding(message, (byte) 3);
            }
            if(callbackQuery.getData().equals("/4")){
                userService.changeRounding(message, (byte) 4);
            }
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
                if (command.equals("/start")) {
                    userService.addUser(message);
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
