package ua.goit.moneybot.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataStorage {
    ArrayList<UserInformation> userInformationArrayList = new ArrayList<>();

    public void addingNewUser(String userName, long userChatId, UserSettings userSettings) {
        UserInformation user = new UserInformation(userName, userChatId, userSettings);
        userInformationArrayList.add(user);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(user);
        File file = new File("database.txt");
        try (FileWriter writer = new FileWriter(file, true)) {
            String text = json;
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateUserSettings(String userName, long userChatId, UserSettings userSettings) {
        UserInformation user = new UserInformation(userName, userChatId, userSettings);
        for (int i = 0; i < userInformationArrayList.size(); i++)
            if (userChatId == userInformationArrayList.get(i).chatId) {
                userInformationArrayList.set(i, user);
            }
    }
}
