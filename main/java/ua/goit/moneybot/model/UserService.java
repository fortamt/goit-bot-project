package ua.goit.moneybot.model;

import org.telegram.telegrambots.meta.api.objects.Message;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {

    private static UserService userService;

    private List<User> userList;

    private UserService(){
        this.userList = new ArrayList<>();
    }

    public static UserService create(){
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public User getUser(Message message){
        for(User user : userList){
            if(user.getChatId().equals(message.getChatId())){
                return user;
            }
        }
        return null;
    }

    public void addUser(Message message) {
        User userTemp = null;
        for(User user : userList){
            if(user.getChatId().equals(message.getChatId())){
                userTemp = user;
            }
        }
        if(userTemp.equals(null)){
            userList.add(new User(message.getChatId()));
        }
    }

    public String getInfo(Message message){
        User user = getUser(message);
        StringBuilder result = new StringBuilder();
        if(user.isUsd()){
            result.append("Курс в ").append(user.getSelectedBank()).append("USD/UAH")
                    .append("\nПокупка: ").append(getUsdBuy(message))
                    .append("\nПродажа: ").append(getUsdSell(message));
        }
        return result.toString();
    }

    private String getUsdBuy(Message message){
        BigDecimal usdBuy = new BigDecimal("0.0");
        Facade facade = new Facade();
        List<BankResponse> bank = facade.getResponseFromBank(getUser(message));
        for(BankResponse bankResponse : bank){
            if(bankResponse.getBankName().equals(getUser(message).getSelectedBank())){
                usdBuy = new BigDecimal(bankResponse.getBuyRate().toString());
            }
        }
        return usdBuy.toString();
    }

    private String getUsdSell(Message message){
        BigDecimal usdSell = new BigDecimal("0.0");
        Facade facade = new Facade();
        List<BankResponse> bank = facade.getResponseFromBank(getUser(message));
        for(BankResponse bankResponse : bank){
            if(bankResponse.getBankName().equals(getUser(message).getSelectedBank())){
                usdSell = new BigDecimal(bankResponse.getSellRate().toString());
            }
        }
        return usdSell.toString();
    }

    public void changeBank(Message message, String selectedBank) {

    }

    public void changeRounding(Message message, byte digitAfterComa) {

    }

    public void changeCurrencyUSD(Message message, boolean usd) {

    }

    public void changeCurrencyEUR(Message message, boolean eur) {

    }

    public void changeCurrencyRUB(Message message, boolean rub) {

    }

//    public void editTimeReminder(Message message, boolean notification, byte notificationTime) {
//        if (notification) userList.get(message.getChatId()).setNotificationTime(notificationTime);
//        else userList.get(message.getChatId()).setNotification(false);
//    }


}
