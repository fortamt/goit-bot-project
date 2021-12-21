package user;

public class UserSettings {
    String bank;
    String originalCurrency;
    String targetCurrency;
    public int symbolsAfterComma = 2;

    public UserSettings(String bank, String originalCurrency, String targetCurrency, int symbolsAfterComma) {
        this.bank = bank;
        this.originalCurrency = originalCurrency;
        this.targetCurrency = targetCurrency;
        this.symbolsAfterComma = symbolsAfterComma;
    }
}
