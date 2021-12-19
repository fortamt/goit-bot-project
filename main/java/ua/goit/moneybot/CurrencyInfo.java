package ua.goit.moneybot;

import java.math.BigDecimal;
import java.util.Objects;

class CurrencyInfo{
    int currencyCodeA;
    int currencyCodeB;
    int date;
    BigDecimal rateSell;
    BigDecimal rateBuy;

    public int getCurrencyCodeA() {
        return currencyCodeA;
    }

    public BigDecimal getRateSell() {
        return rateSell;
    }

    public BigDecimal getRateBuy() {
        return rateBuy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrencyInfo that = (CurrencyInfo) o;
        return currencyCodeA == that.currencyCodeA && currencyCodeB == that.currencyCodeB && date == that.date && Objects.equals(rateSell, that.rateSell) && Objects.equals(rateBuy, that.rateBuy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currencyCodeA, currencyCodeB, date, rateSell, rateBuy);
    }

    @Override
    public String toString() {
        return "CurrencyInfo{" +
                "currencyCodeA=" + currencyCodeA +
                ", currencyCodeB=" + currencyCodeB +
                ", date=" + date +
                ", rateSell=" + rateSell +
                ", rateBuy=" + rateBuy +
                '}';
    }
}