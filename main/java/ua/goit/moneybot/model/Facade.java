package ua.goit.moneybot.model;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade {

    Map<Long, UserSettings> users = new HashMap<>();

    public List<BankResponse> getResponseFromBank(UserSettings userSettings, Message message) throws IOException, InterruptedException {
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

//    public String getInfo(UserSettings userSettings, Message message){
//
//        System.out.println("Курс в " + getBank(userSettings, message) );
//    }  в процессе

    public String getBank(Message message){
        for(Map.Entry<Long, UserSettings> entry : users.entrySet()){
            if(entry.getKey().equals(message.getChatId())){
                UserSettings user = entry.getValue();
                return user.getSelectedBank();
            }
        }
        return "Error";
    }

//       в процессе



}
