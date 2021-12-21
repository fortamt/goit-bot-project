package user;

public class UserInformation {
    String name;
    int chatId;
    UserSettings userSettings;

    public UserInformation(String name, int chatId, UserSettings userSettings) {
        this.name = name;
        this.chatId = chatId;
        this.userSettings = userSettings;
    }
}
