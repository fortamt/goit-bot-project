package ua.goit.moneybot.view;


public class ConsoleView implements ConsoleViewImpl {


    @Override
    public void write(String message) {
        System.out.println(message);
    }
}
