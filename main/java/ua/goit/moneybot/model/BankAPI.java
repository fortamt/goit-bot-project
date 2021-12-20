package ua.goit.moneybot.model;

import java.math.BigDecimal;

public interface BankAPI {

    BigDecimal getUsdBuy();
    BigDecimal getUsdSell();
    BigDecimal getEurBuy();
    BigDecimal getEurSell();
    BigDecimal getRubBuy();
    BigDecimal getRubSell();

}
