package ua.goit.moneybot.model;

import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Facade {

    Map<Long, UserSettings> users = new HashMap<>();

    public List<BankResponse> getResponseFromBank(UserSettings userSettings, Message message) throws IOException, InterruptedException {
        if(userSettings.getSelectedBank().equals("Monobank")){
            MonobankAPI monobankAPI = new MonobankAPI();
            return monobankAPI.getActualCurrency();
        } else if (userSettings.getSelectedBank().equals("PrivatBank")){
            PrivatBankAPI privatBankAPI = new PrivatBankAPI();
//            return privatBankAPI.getActualCurrency();    задача для приват апи (аналогия монобанк апи)
        } else if (userSettings.getSelectedBank().equals("NBU")){
            NbuAPI nbuAPI = new NbuAPI();
//            return nbuAPI.getActualCurrency();   задача для нбу апи
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
