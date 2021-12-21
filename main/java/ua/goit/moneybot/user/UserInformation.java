package ua.goit.moneybot.user;

public class UserInformation {
    String name = "User";
    int chatId = 1;
    UserSettings userSettings = new UserSettings();

    public UserInformation() {
    }

    public UserInformation(String name, int chatId, UserSettings userSettings) {
        this.name = name;
        this.chatId = chatId;
        this.userSettings = userSettings;
    }
}
