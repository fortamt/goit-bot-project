package ua.goit.moneybot.model;

import java.util.List;

public class Facade {

    public List<BankResponse> getResponseFromBank(User user){
        CashService cashService = new CashService();
        if(user.getSelectedBank().equals("Monobank")){
            return cashService.getCashedMonobankCurrency();
        } else if (user.getSelectedBank().equals("PrivatBank")){
            return cashService.getCashedPrivatBankCurrency();
        } else if (user.getSelectedBank().equals("NBU")){
            return cashService.getCashedNBUCurrency();
        }
        return null;
    }



//    public void addUser(Map<Long, User> users, Message message) {
//        userService.addUser(users, message);
//    }
//
//    public String getInfo(Map<Long, User> users, Message message){
//        return  "Курс в " + userService.getUserSettings(users, message).getSelectedBank() +
//                "\n" + showCourses(users, message);
//    }
//
//    public String showCourses(Map<Long, User> users, Message message){
//        StringBuilder result = new StringBuilder();
//        if(userService.getUserSettings(users, message).isUsd()){
//            return result.append(getUsd(getResponseFromBank(userService.getUserSettings(users, message)))).toString();
//        }
//        return "Error";
//    }
//
//    private String getUsd(List<BankResponse> list){
//        for (BankResponse bankResponse : list){
//            if(bankResponse.getCurrency().equals("USD")){
//                return "Покупка: " + bankResponse.getBuyRate().toString() + "\nПродажа: " + bankResponse.getSellRate().toString();
//            }
//        }
//        return "Error";
//    }


}
