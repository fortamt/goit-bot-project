package ua.goit.moneybot.model;

import java.io.IOException;
import java.math.BigDecimal;

public class BankResponse{

    BigDecimal usdBuy;
    BigDecimal usdSell = new BigDecimal("27.3458");
    BigDecimal eurBuy;
    BigDecimal eurSell;
    BigDecimal rubBuy;
    BigDecimal rubSell;


    public void setCurrency(UserSettings userSettings) throws IOException, InterruptedException {
//        if(userSettings.chosedBank == "mono") {
            MonobankAPI monobankAPI = new MonobankAPI();
            usdBuy = monobankAPI.getUsdBuy();
//        }
    }

    public BigDecimal getUsdBuy() {
        return usdBuy;
    }

    public BigDecimal getUsdSell() {
        return usdSell;
    }

    public BigDecimal getEurBuy() {
        return eurBuy;
    }

    public BigDecimal getEurSell() {
        return eurSell;
    }

    public BigDecimal getRubBuy() {
        return rubBuy;
    }

    public BigDecimal getRubSell() {
        return rubSell;
    }
}