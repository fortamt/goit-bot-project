package ua.goit.moneybot.user;

public class UserSettings {
    String bank = "MonoBank";
    String originalCurrency = "USD";
    String targetCurrency = "EUR";
    int symbolsAfterComma = 2;
    int alertTime = 9;

    public UserSettings() {
    }

    public UserSettings(String bank, String originalCurrency, String targetCurrency, int symbolsAfterComma, int alertTime) {
        this.bank = bank;
        this.originalCurrency = originalCurrency;
        this.targetCurrency = targetCurrency;
        this.symbolsAfterComma = symbolsAfterComma;
        this.alertTime = alertTime;
    }
}
