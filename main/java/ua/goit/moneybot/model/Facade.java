package ua.goit.moneybot.model;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade {

    UserService userService = new UserService();

    public List<BankResponse> getResponseFromBank(UserSettings userSettings){
        CashService cashService = new CashService();
        if(userSettings.getSelectedBank().equals("Monobank")){
            return cashService.getCashedMonobankCurrency();
        } else if (userSettings.getSelectedBank().equals("PrivatBank")){
            return cashService.getCashedPrivatBankCurrency();
        } else if (userSettings.getSelectedBank().equals("NBU")){
            return cashService.getCashedNBUCurrency();
        }
        return null;
    }


    public void addUser(Map<Long, UserSettings> users, Message message) {
        userService.addUser(users, message);
    }

    public String getInfo(Map<Long, UserSettings> users, Message message){
        return  "Курс в " + userService.getUserSettings(users, message).getSelectedBank() +
                "\n" + showCourses(users, message);
    }

    public String showCourses(Map<Long, UserSettings> users, Message message){
        StringBuilder result = new StringBuilder();
        if(userService.getUserSettings(users, message).isUsd()){
            return result.append(getUsd(getResponseFromBank(userService.getUserSettings(users, message)))).toString();
        }
        return "Error";
    }

    public String getUsd(List<BankResponse> list){
        for (BankResponse bankResponse : list){
            if(bankResponse.getCurrency().equals("USD")){
                return "Покупка: " + bankResponse.getBuyRate().toString() + "\nПродажа: " + bankResponse.getSellRate().toString();
            }
        }
        return "Error";
    }


}
