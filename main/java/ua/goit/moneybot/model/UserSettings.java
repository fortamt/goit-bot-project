package ua.goit.moneybot.model;

import org.telegram.telegrambots.meta.api.objects.User;

import java.util.Objects;

public class UserSettings {

    private String name;
    private String surname;
    private byte digitAfterComa;
    private boolean USD;
    private boolean EUR;
    private boolean RUB;
    private String selectedBank;
    private boolean notification;
    private byte notificationTime;

    public UserSettings(User user) {
        this.name = user.getFirstName();
        this.surname = user.getLastName();
        this.digitAfterComa = 2;
        this.USD = true;
        this.selectedBank = "Monobank";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public byte getDigitAfterComa() {
        return digitAfterComa;
    }

    public void setDigitAfterComa(byte digitAfterComa) {
        this.digitAfterComa = digitAfterComa;
    }

    public boolean isUSD() {
        return USD;
    }

    public void setUSD(boolean USD) {
        this.USD = USD;
    }

    public boolean isEUR() {
        return EUR;
    }

    public void setEUR(boolean EUR) {
        this.EUR = EUR;
    }

    public boolean isRUB() {
        return RUB;
    }

    public void setRUB(boolean RUB) {
        this.RUB = RUB;
    }

    public String getSelectedBank() {
        return selectedBank;
    }

    public void setSelectedBank(String selectedBank) {
        this.selectedBank = selectedBank;
    }

    public boolean isNotification() {
        return notification;
    }

    public void setNotification(boolean notification) {
        this.notification = notification;
    }

    public byte getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(byte notificationTime) {
        this.notificationTime = notificationTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSettings that = (UserSettings) o;
        return digitAfterComa == that.digitAfterComa && USD == that.USD && EUR == that.EUR && RUB == that.RUB && notification == that.notification && notificationTime == that.notificationTime && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(selectedBank, that.selectedBank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, digitAfterComa, USD, EUR, RUB, selectedBank, notification, notificationTime);
    }

    @Override
    public String toString() {
        return "UserSettings{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", digitAfterComa=" + digitAfterComa +
                ", USD=" + USD +
                ", EUR=" + EUR +
                ", RUB=" + RUB +
                ", selectedBank='" + selectedBank + '\'' +
                ", notification=" + notification +
                ", notificationTime=" + notificationTime +
                '}';
    }
}

