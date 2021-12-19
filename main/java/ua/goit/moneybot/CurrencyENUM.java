package ua.goit.moneybot;

public enum CurrencyENUM {
    USD("USD"),
    EUR("EUR"),
    RUB("RUB");

    private String currencyName;

    CurrencyENUM(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyName(){
        return currencyName;
    }
}
