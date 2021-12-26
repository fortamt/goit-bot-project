package ua.goit.moneybot.view;

import ua.goit.moneybot.model.BankResponse;

import java.math.BigDecimal;

public class ConsoleView implements ConsoleViewImpl {


    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
