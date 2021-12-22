package ua.goit.moneybot.model;

import java.io.IOException;
import java.math.BigDecimal;

public class BankResponse {

    private BigDecimal usdBuy;
    private BigDecimal usdSell;
    private BigDecimal eurBuy;
    private BigDecimal eurSell;
    private BigDecimal rubBuy;
    private BigDecimal rubSell;


    private void setCurrency(UserSettings userSettings) throws IOException, InterruptedException {
        //
    }

    public BigDecimal getUsdBuy() {
        return usdBuy;
    }

    public void setUsdBuy(BigDecimal usdBuy) {
        this.usdBuy = usdBuy;
    }

    public BigDecimal getUsdSell() {
        return usdSell;
    }

    public void setUsdSell(BigDecimal usdSell) {
        this.usdSell = usdSell;
    }

    public BigDecimal getEurBuy() {
        return eurBuy;
    }

    public void setEurBuy(BigDecimal eurBuy) {
        this.eurBuy = eurBuy;
    }

    public BigDecimal getEurSell() {
        return eurSell;
    }

    public void setEurSell(BigDecimal eurSell) {
        this.eurSell = eurSell;
    }

    public BigDecimal getRubBuy() {
        return rubBuy;
    }

    public void setRubBuy(BigDecimal rubBuy) {
        this.rubBuy = rubBuy;
    }

    public BigDecimal getRubSell() {
        return rubSell;
    }

    public void setRubSell(BigDecimal rubSell) {
        this.rubSell = rubSell;
    }
}