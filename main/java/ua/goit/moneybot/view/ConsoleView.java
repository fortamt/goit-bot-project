package ua.goit.moneybot.view;

import ua.goit.moneybot.model.BankResponse;

import java.math.BigDecimal;

public class ConsoleView implements View {

    @Override
    public BigDecimal getUsdBuy(BankResponse bankResponse) {
        return null;
    }

    @Override
    public BigDecimal getUsdSell(BankResponse bankResponse) {
        return null;
    }

    @Override
    public BigDecimal getEurBuy(BankResponse bankResponse) {
        return null;
    }

    @Override
    public BigDecimal getEurSell(BankResponse bankResponse) {
        return null;
    }

    @Override
    public BigDecimal getRubBuy(BankResponse bankResponse) {
        return null;
    }

    @Override
    public BigDecimal getRubSell(BankResponse bankResponse) {
        return null;
    }
}
