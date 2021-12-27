package ua.goit.moneybot.model;

import org.telegram.telegrambots.meta.api.objects.Message;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {

    private static UserService userService;

    public static UserService getUserService() {
        return userService;
    }

    public List<User> getUserList() {
        return userList;
    }

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
                    result.append("\nUSD/UAH").append("\nПокупка: ").append(new BigDecimal(bankResponse.getBuyRate().setScale(user.getDigitAfterComa(), RoundingMode.DOWN).toString()))
                            .append("\nПродажа: ").append(new BigDecimal(bankResponse.getSellRate().setScale(user.getDigitAfterComa(), RoundingMode.DOWN).toString()));
                }
            }
        }
        if(user.isEur()){
            for(BankResponse bankResponse : bank){
                if(bankResponse.getCurrency().equals(CurrencyEnum.EUR.getCodeString())){
                    result.append("\nEUR/UAH").append("\nПокупка: ").append(new BigDecimal(bankResponse.getBuyRate().setScale(user.getDigitAfterComa(), RoundingMode.DOWN).toString()))
                            .append("\nПродажа: ").append(new BigDecimal(bankResponse.getSellRate().setScale(user.getDigitAfterComa(), RoundingMode.DOWN).toString()));
                }
            }
        }
        if(user.isRub()){
            for(BankResponse bankResponse : bank){
                if(bankResponse.getCurrency().equals(CurrencyEnum.RUB.getCodeString())){
                    result.append("\nRUB/UAH").append("\nПокупка: ").append(new BigDecimal(bankResponse.getBuyRate().setScale(user.getDigitAfterComa(), RoundingMode.DOWN).toString()))
                            .append("\nПродажа: ").append(new BigDecimal(bankResponse.getSellRate().setScale(user.getDigitAfterComa(), RoundingMode.DOWN).toString()));
                }
            }
        }
        return result.toString();
    }


    public void changeBank(Message message, String selectedBank) {
        getUser(message).setSelectedBank(selectedBank);
    }

    public void changeSchedule(Message message, byte time) {
        getUser(message).setNotificationTime(time);
    }

    public void changeRounding(Message message, byte digitAfterComa) {
        getUser(message).setDigitAfterComa(digitAfterComa);
    }

    public void changeCurrencyUSD(Message message) {
        if(getUser(message).isUsd()){
            getUser(message).setUsd(false);
        } else if(!getUser(message).isUsd()){
            getUser(message).setUsd(true);
        }
    }

    public void changeCurrencyEUR(Message message) {
        if(getUser(message).isEur()){
            getUser(message).setEur(false);
        } else if(!getUser(message).isEur()){
            getUser(message).setEur(true);
        }
    }

    public void changeCurrencyRUB(Message message) {
        if(getUser(message).isRub()){
            getUser(message).setRub(false);
        } else if(!getUser(message).isRub()){
            getUser(message).setRub(true);
        }
    }

//    public void editTimeReminder(Message message, boolean notification, byte notificationTime) {
//        if (notification) userList.get(message.getChatId()).setNotificationTime(notificationTime);
//        else userList.get(message.getChatId()).setNotification(false);
//    }


}
