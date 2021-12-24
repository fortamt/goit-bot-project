package ua.goit.moneybot.controller;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Keyboards {

    public List<List<InlineKeyboardButton>> getMainMenu(){
        List<List<InlineKeyboardButton>> menu = new ArrayList<>();
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Получить инфо")
                                .callbackData("get_info")
                                .build()));
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Настройки")
                                .callbackData("settings")
                                .build()));
        return menu;
    }

    public List<List<InlineKeyboardButton>> getSettingsMenu(){
        List<List<InlineKeyboardButton>> menu = new ArrayList<>();
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Кол-во знаков после запятой")
                                .callbackData("digitAfterComa")
                                .build()));
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Банк")
                                .callbackData("banks")
                                .build()));
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Валюты")
                                .callbackData("currency")
                                .build()));
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Время оповещений")
                                .callbackData("schedule")
                                .build()));
        return menu;
    }

    public List<List<InlineKeyboardButton>> getDigitMenu(){
        List<List<InlineKeyboardButton>> menu = new ArrayList<>();
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("2")
                                .callbackData("--------------")
                                .build()));
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("3")
                                .callbackData("--------------")
                                .build()));
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("4")
                                .callbackData("--------------")
                                .build()));
        return menu;
    }

    public List<List<InlineKeyboardButton>> getBankMenu(){
        List<List<InlineKeyboardButton>> menu = new ArrayList<>();
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("Monobank")
                                .callbackData("--------------")
                                .build()));
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("PrivatBank")
                                .callbackData("--------------")
                                .build()));
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("NBU")
                                .callbackData("--------------")
                                .build()));
        return menu;
    }

    public List<List<InlineKeyboardButton>> getCurrencyMenu() {
        List<List<InlineKeyboardButton>> menu = new ArrayList<>();
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("USD")
                                .callbackData("--------------")
                                .build()));
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("EUR")
                                .callbackData("--------------")
                                .build()));
        menu.add(
                Arrays.asList(
                        InlineKeyboardButton.builder()
                                .text("RUB")
                                .callbackData("--------------")
                                .build()));
        return menu;
    }

    public List<KeyboardRow> getTimeAlert() {
        List<KeyboardRow> timeAlertKeyboard = new ArrayList<>();
        KeyboardRow rowFirst = new KeyboardRow();
            rowFirst.add("9");
            rowFirst.add("10");
            rowFirst.add("11");
        timeAlertKeyboard.add(rowFirst);
        KeyboardRow rowSecond = new KeyboardRow();
            rowSecond.add("12");
            rowSecond.add("13");
            rowSecond.add("14");
        timeAlertKeyboard.add(rowSecond);
        KeyboardRow rowThird = new KeyboardRow();
            rowThird.add("15");
            rowThird.add("16");
            rowThird.add("17");
        timeAlertKeyboard.add(rowThird);
        KeyboardRow rowFourth = new KeyboardRow();
            rowFourth.add("18");
            rowFourth.add("Выключить уведомления");
        timeAlertKeyboard.add(rowFourth);
        return timeAlertKeyboard;
    }



}
