package ua.goit.moneybot.model;

import java.math.BigDecimal;
import java.util.Objects;

class MonobankInfo {
    private int currencyCodeA;
    private int currencyCodeB;
    private int date;
    private BigDecimal rateSell;
    private BigDecimal rateBuy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonobankInfo that = (MonobankInfo) o;
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

    public int getCurrencyCodeA() {
        return currencyCodeA;
    }

    public int getCurrencyCodeB() {
        return currencyCodeB;
    }

    public int getDate() {
        return date;
    }

    public BigDecimal getRateSell() {
        return rateSell;
    }

    public BigDecimal getRateBuy() {
        return rateBuy;
    }
}