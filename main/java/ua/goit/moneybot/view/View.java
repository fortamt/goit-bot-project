package ua.goit.moneybot.view;

import ua.goit.moneybot.model.BankResponse;

import java.math.BigDecimal;

public interface View {
    BigDecimal getUsdBuy(BankResponse bankResponse);
    BigDecimal getUsdSell(BankResponse bankResponse);
    BigDecimal getEurBuy(BankResponse bankResponse);
    BigDecimal getEurSell(BankResponse bankResponse);
    BigDecimal getRubBuy(BankResponse bankResponse);
    BigDecimal getRubSell(BankResponse bankResponse);
}
