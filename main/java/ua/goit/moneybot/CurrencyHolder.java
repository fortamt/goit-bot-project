package ua.goit.moneybot;

import java.math.BigDecimal;

public class CurrencyHolder {
    private BigDecimal usdRateSell;
    private BigDecimal usdRateBuy;
    private BigDecimal rubRateSell;
    private BigDecimal rubRateBuy;
    private BigDecimal eurRateSell;
    private BigDecimal eurRateBuy;

    public BigDecimal getUsdRateSell() {
        return usdRateSell;
    }

    public void setUsdRateSell(BigDecimal usdRateSell) {
        this.usdRateSell = usdRateSell;
    }

    public BigDecimal getUsdRateBuy() {
        return usdRateBuy;
    }

    public void setUsdRateBuy(BigDecimal usdRateBuy) {
        this.usdRateBuy = usdRateBuy;
    }

    public BigDecimal getRubRateSell() {
        return rubRateSell;
    }

    public void setRubRateSell(BigDecimal rubRateSell) {
        this.rubRateSell = rubRateSell;
    }

    public BigDecimal getRubRateBuy() {
        return rubRateBuy;
    }

    public void setRubRateBuy(BigDecimal rubRateBuy) {
        this.rubRateBuy = rubRateBuy;
    }

    public BigDecimal getEurRateSell() {
        return eurRateSell;
    }

    public void setEurRateSell(BigDecimal eurRateSell) {
        this.eurRateSell = eurRateSell;
    }

    public BigDecimal getEurRateBuy() {
        return eurRateBuy;
    }

    public void setEurRateBuy(BigDecimal eurRateBuy) {
        this.eurRateBuy = eurRateBuy;
    }
}