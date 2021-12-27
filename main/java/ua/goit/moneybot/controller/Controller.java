package ua.goit.moneybot.controller;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.goit.moneybot.model.Scheduler;
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
                        .text(userService.getInfo(message))
                        .chatId(message.getChatId().toString())
                        .build());
                execute(SendMessage.builder()
                        .text("Главное меню")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getMainMenu()).build())
                        .build());
            }
            if (callbackQuery.getData().equals("settings")) {
                execute(SendMessage.builder()
                        .text("Настройки")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getSettingsMenu()).build())
                        .build());
            }
            if (callbackQuery.getData().equals("digitAfterComa")) {
                execute(SendMessage.builder()
                        .text("Выберите количество знаков после запятой")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getDigitMenu(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("banks")) {
                execute(SendMessage.builder()
                        .text("Выберите желаемый банк. Только один")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getBankMenu(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("currency")) {
                execute(SendMessage.builder()
                        .text("Выберите нужные курсы, можно несколько")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getCurrencyMenu(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("schedule")) {
                execute(SendMessage.builder()
                        .text("Выберете время оповещения")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("USD")) {
                userService.changeCurrencyUSD(message);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getCurrencyMenu(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("EUR")) {
                userService.changeCurrencyEUR(message);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getCurrencyMenu(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("RUB")) {
                userService.changeCurrencyRUB(message);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getCurrencyMenu(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("/2")) {
                userService.changeRounding(message, (byte) 2);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getDigitMenu(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("/3")) {
                userService.changeRounding(message, (byte) 3);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getDigitMenu(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("/4")) {
                userService.changeRounding(message, (byte) 4);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getDigitMenu(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("Monobank")) {
                userService.changeBank(message, "Monobank");
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getBankMenu(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("PrivatBank")) {
                userService.changeBank(message, "PrivatBank");
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getBankMenu(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("NBU")) {
                userService.changeBank(message, "NBU");
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getBankMenu(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("9:00")) {
                userService.changeSchedule(message, (byte) 9);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("10:00")) {
                userService.changeSchedule(message, (byte) 10);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("11:00")) {
                userService.changeSchedule(message, (byte) 11);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("12:00")) {
                userService.changeSchedule(message, (byte) 12);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("13:00")) {
                userService.changeSchedule(message, (byte) 13);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("14:00")) {
                userService.changeSchedule(message, (byte) 14);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("15:00")) {
                userService.changeSchedule(message, (byte) 15);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("16:00")) {
                userService.changeSchedule(message, (byte) 16);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("17:00")) {
                userService.changeSchedule(message, (byte) 17);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("18:00")) {
                userService.changeSchedule(message, (byte) 18);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("0")) {
                userService.changeSchedule(message, (byte) 0);
                execute(EditMessageReplyMarkup.builder()
                        .chatId(message.getChatId().toString())
                        .messageId(message.getMessageId())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getTimeAlert(message)).build())
                        .build());
            }
            if (callbackQuery.getData().equals("/start")) {
                Scheduler scheduler = new Scheduler();
                scheduler.schedulerStart(message);
                execute(SendMessage.builder()
                        .text("Добро пожаловать. Этот бот поможет отслеживать актуальные курсы валют")
                        .chatId(message.getChatId().toString())
                        .replyMarkup(InlineKeyboardMarkup.builder().keyboard(keyboards.getMainMenu()).build())
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
