package ua.goit.moneybot.model;

import java.io.IOException;

public interface Bank {
    void setCurrency(UserSettings userSettings) throws IOException, InterruptedException;
}
