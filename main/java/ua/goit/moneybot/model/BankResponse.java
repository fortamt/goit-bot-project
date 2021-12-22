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
            monobankAPI.getUsdBuy();
            monobankAPI.getUsdSell();
            monobankAPI.getEurBuy();
            monobankAPI.getEurSell();
            monobankAPI.getRubBuy();
            monobankAPI.getRubSell();
        } else if (userSettings.getBankName().equals("PrivatBank")){
            PrivatBankankAPI privatBankankAPI = new PrivatBankankAPI();
            privatBankankAPI.getUsdBuy();
            privatBankankAPI.getUsdSell();
            privatBankankAPI.getEurBuy();
            privatBankankAPI.getEurSell();
            privatBankankAPI.getRubBuy();
            privatBankankAPI.getRubSell();
        } else if (userSettings.getBankName().equals("NBU")){
            NBUAPI NBUAPI = new NBUAPI();
            NBUAPI.getUsdBuy();
            NBUAPI.getUsdSell();
            NBUAPI.getEurBuy();
            NBUAPI.getEurSell();
            NBUAPI.getRubBuy();
            NBUAPI.getRubSell();
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