package ua.goit.moneybot;

import java.math.BigDecimal;
import java.util.Objects;

public class NbuInfo {

    int r030;
    String txt;
    String cc;
    String exchangedate;
    BigDecimal rate;


    @Override
    public int hashCode() {
        return Objects.hash(cc, txt, rate, exchangedate, r030);
    }

    @Override
    public String toString() {
        return "\n{ " +
                "\nExchange Currency = " + cc +
                ", \nExchangeDate = " + exchangedate +
                ", \nText Name = " + txt +
                ", \nBuy = " + rate +
                "\n}\n";
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getR030() {
        return r030;
    }

    public void setR030(int r030) {
        this.r030 = r030;
    }
}
