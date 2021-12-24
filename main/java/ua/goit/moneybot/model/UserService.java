package ua.goit.moneybot.model;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.objects.Message;


import java.util.HashMap;
import java.util.Map;

public class UserService {


    public void addUser(Map<Long, UserSettings> users, Message message) {
        users.put(message.getChatId(), new UserSettings());
    }

    public void changeBank(Map<Long, UserSettings> users, Message message, String selectedBank) {
        users.get(message.getChatId()).setSelectedBank(selectedBank);
    }

    public void changeRounding(Map<Long, UserSettings> users, Message message, byte digitAfterComa) {
        users.get(message.getChatId()).setDigitAfterComa(digitAfterComa);
    }

    public void changeCurrencyUSD(Map<Long, UserSettings> users, Message message, boolean usd) {
        users.get(message.getChatId()).setUsd(usd);
    }

    public void changeCurrencyEUR(Map<Long, UserSettings> users, Message message, boolean eur) {
        users.get(message.getChatId()).setUsd(eur);
    }

    public void changeCurrencyRUB(Map<Long, UserSettings> users, Message message, boolean rub) {
        users.get(message.getChatId()).setUsd(rub);
    }

    public void editTimeReminder(Map<Long, UserSettings> users, Message message, boolean notification, byte notificationTime) {
        if (notification) users.get(message.getChatId()).setNotificationTime(notificationTime);
        else users.get(message.getChatId()).setNotification(false);
    }

    public UserSettings getUserSettings(Map<Long, UserSettings> users, Message message){
        return users.get(message.getChatId());
    }


//    private void renewingList(){
//        //преобразовать json в hashmap users
//    }
//
//    private void savingChanges(Map users){
//        try (FileWriter writer = new FileWriter("users.json")) {
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            String json = gson.toJson(users);
//            writer.write(json);
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
//        }
//    }

}
