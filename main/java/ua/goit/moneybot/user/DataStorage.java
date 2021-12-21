package ua.goit.moneybot.user;

import java.util.ArrayList;

public class DataStorage {
    ArrayList<UserInformation> userInformationArrayList = new ArrayList<>();

    public void addingNewUser(String userName, int userChatId, UserSettings userSettings) {
        UserInformation user = new UserInformation(userName, userChatId, userSettings);
        userInformationArrayList.add(user);
    }

    public void updateUserSettings(String userName, int userChatId, UserSettings userSettings) {
        UserInformation user = new UserInformation(userName, userChatId, userSettings);
        for (int i = 0; i < userInformationArrayList.size(); i++)
            if (userChatId == userInformationArrayList.get(i).chatId) {
                userInformationArrayList.set(i, user);
            }
    }
}
