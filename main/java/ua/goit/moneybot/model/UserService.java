package ua.goit.moneybot.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserService {
    private final Gson GSON = new Gson();
    Map<Long, UserSettings> users = new HashMap<>();

    public void addUser(Message message, UserSettings userSettings){
        renewingList();
        users.put(message.getChatId(), userSettings);
        savingChanges(users);
    }

    private void renewingList(){
        //преобразовать json в hashmap users
    }

    private void savingChanges(Map users){
        try (FileWriter writer = new FileWriter("users.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(users);
            writer.write(json);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
