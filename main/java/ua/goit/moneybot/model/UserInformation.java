package ua.goit.moneybot.model;

public class UserInformation {
    String name = "User";
    long chatId = 1;
    UserSettings userSettings = new UserSettings();

    public UserInformation() {
    }

    public UserInformation(String name, long chatId, UserSettings userSettings) {
        this.name = name;
        this.chatId = chatId;
        this.userSettings = userSettings;
    }
}
