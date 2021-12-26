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
        if(userTemp == (null)){
            userList.add(new User(message.getChatId()));
        }
    }

    public String getInfo(Message message){
        BigDecimal usdBuy = new BigDecimal("0.0");
        BigDecimal usdSell = new BigDecimal("0.0");
        User user = getUser(message);
        StringBuilder result = new StringBuilder();
        Facade facade = new Facade();
        List<BankResponse> bank = facade.getResponseFromBank(getUser(message));
        result.append("Курс в ").append(user.getSelectedBank());
        if(user.isUsd()){
            for(BankResponse bankResponse : bank){
                if(bankResponse.getCurrency().equals(CurrencyEnum.USD.getCodeString())){
                    result.append("\nUSD/UAH").append("\nПокупка: ").append(new BigDecimal(bankResponse.getBuyRate().toString()))
                            .append("\nПродажа: ").append(new BigDecimal(bankResponse.getSellRate().toString()));
                }
            }
        }
        if(user.isEur()){
            for(BankResponse bankResponse : bank){
                if(bankResponse.getCurrency().equals(CurrencyEnum.EUR.getCodeString())){
                    result.append("\nEUR/UAH").append("\nПокупка: ").append(new BigDecimal(bankResponse.getBuyRate().toString()))
                            .append("\nПродажа: ").append(new BigDecimal(bankResponse.getSellRate().toString()));
                }
            }
        }
        if(user.isRub()){
            for(BankResponse bankResponse : bank){
                if(bankResponse.getCurrency().equals(CurrencyEnum.RUB.getCodeString())){
                    result.append("\nRUB/UAH").append("\nПокупка: ").append(new BigDecimal(bankResponse.getBuyRate().toString()))
                            .append("\nПродажа: ").append(new BigDecimal(bankResponse.getSellRate().toString()));
                }
            }
        }
        return result.toString();
    }


    public void changeBank(Message message, String selectedBank) {

    }

    public void changeRounding(Message message, byte digitAfterComa) {
        getUser(message).setDigitAfterComa(digitAfterComa);
    }

    public void changeCurrencyUSD(Message message) {
        if(getUser(message).isUsd() == true){
            getUser(message).setUsd(false);
            return;
        } else if(getUser(message).isUsd() == false){
            getUser(message).setUsd(true);
        }
    }

    public void changeCurrencyEUR(Message message) {
        if(getUser(message).isEur() == true){
            getUser(message).setEur(false);
            return;
        } else if(getUser(message).isEur() == false){
            getUser(message).setEur(true);
        }
    }

    public void changeCurrencyRUB(Message message) {
        if(getUser(message).isRub() == true){
            getUser(message).setRub(false);
            return;
        } else if(getUser(message).isRub() == false){
            getUser(message).setRub(true);
        }
    }

//    public void editTimeReminder(Message message, boolean notification, byte notificationTime) {
//        if (notification) userList.get(message.getChatId()).setNotificationTime(notificationTime);
//        else userList.get(message.getChatId()).setNotification(false);
//    }


}
