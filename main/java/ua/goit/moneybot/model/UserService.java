package ua.goit.moneybot.model;

import com.google.gson.Gson;
import org.telegram.telegrambots.meta.api.objects.Message;


import java.util.HashMap;
import java.util.Map;

public class UserService {
    //private final Gson GSON = new Gson();

    Map<Long, UserSettings> users = new HashMap<>();

    public void addUser(Message message, UserSettings userSettings) {
        users.put(message.getChatId(), userSettings);
    }

    public void changeBank(Message message, String selectedBank) {
        users.get(message.getChatId()).setSelectedBank(selectedBank);
    }

    public void changeRounding(Message message, byte digitAfterComa) {
        users.get(message.getChatId()).setDigitAfterComa(digitAfterComa);
    }

    public void changeCurrencyUSD(Message message, boolean usd) {
        users.get(message.getChatId()).setUsd(usd);
    }

    public void changeCurrencyEUR(Message message, boolean eur) {
        users.get(message.getChatId()).setUsd(eur);
    }

    public void changeCurrencyRUB(Message message, boolean rub) {
        users.get(message.getChatId()).setUsd(rub);
    }

    public void editTimeReminder(Message message, boolean notification, byte notificationTime) {
        if (notification) users.get(message.getChatId()).setNotificationTime(notificationTime);
        else users.get(message.getChatId()).setNotification(false);
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
