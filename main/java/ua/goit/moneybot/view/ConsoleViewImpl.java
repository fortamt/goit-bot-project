package ua.goit.moneybot.view;

import ua.goit.moneybot.model.BankResponse;

import java.math.BigDecimal;

public interface ConsoleViewImpl {
    String getUsdBuy(BankResponse bankResponse);
    String getUsdSell(BankResponse bankResponse);
    String getEurBuy(BankResponse bankResponse);
    String getEurSell(BankResponse bankResponse);
    String getRubBuy(BankResponse bankResponse);
    String getRubSell(BankResponse bankResponse);
}
