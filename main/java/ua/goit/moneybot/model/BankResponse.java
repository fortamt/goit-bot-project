package ua.goit.moneybot.model;

import java.io.IOException;
import java.math.BigDecimal;

public class BankResponse{

    private BigDecimal usdBuy;
    private BigDecimal usdSell;
    private BigDecimal eurBuy;
    private BigDecimal eurSell;
    private BigDecimal rubBuy;
    private BigDecimal rubSell;


    private void setCurrency(UserSettings userSettings) throws IOException, InterruptedException {
        if (userSettings.getBankName().equals("Monobank")){
            MonobankAPI monobankAPI = new MonobankAPI();
            this.usdBuy = monobankAPI.getUsdBuy();
            this.usdSell = monobankAPI.getUsdSell();
            this.eurBuy = monobankAPI.getEurBuy();
            this.eurSell = monobankAPI.getEurSell();
            this.rubBuy = monobankAPI.getRubBuy();
            this.rubSell = monobankAPI.getRubSell();
        } else if (userSettings.getBankName().equals("PrivatBank")){
            PrivatBankankAPI privatBankankAPI = new PrivatBankankAPI();
            this.usdBuy = privatBankankAPI.getUsdBuy();
            this.usdSell = privatBankankAPI.getUsdSell();
            this.eurBuy = privatBankankAPI.getEurBuy();
            this.eurSell = privatBankankAPI.getEurSell();
            this.rubBuy = privatBankankAPI.getRubBuy();
            this.rubSell = privatBankankAPI.getRubSell();
        } else if (userSettings.getBankName().equals("NBU")){
            NBUAPI NBUAPI = new NBUAPI();
            this.usdBuy = NBUAPI.getUsdBuy();
            this.usdSell = NBUAPI.getUsdSell();
            this.eurBuy = NBUAPI.getEurBuy();
            this.eurSell = NBUAPI.getEurSell();
            this.rubBuy = NBUAPI.getRubBuy();
            this.rubSell = NBUAPI.getRubSell();
        }
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