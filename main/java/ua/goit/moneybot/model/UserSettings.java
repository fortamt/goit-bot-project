package ua.goit.moneybot.model;

public class UserSettings {
    String chosedBank;
    int chatId;

    public UserSettings(int chatId) {
        this.chosedBank = "mono";
        this.chatId = chatId;
    }
}
