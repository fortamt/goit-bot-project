package ua.goit.moneybot.model;

import java.math.BigDecimal;
import java.util.Objects;


public class PrivatBankInfo {

    String ccy;
    String base_ccy;
    BigDecimal buy;
    BigDecimal sale;

    @Override
    public int hashCode() {
        return Objects.hash(ccy, base_ccy, buy, sale);
    }

    @Override
    public String toString() {
        return "\n{ " +
                "\nExchange Currency = " + ccy +
                ", \nBase Currency = " + base_ccy +
                ", \nSell = " + sale +
                ", \nBuy = " + buy +
                "\n}\n";
    }

    public String getCcy() {
        return ccy;
    }

    public void setCcy(String ccy) {
        this.ccy = ccy;
    }

    public String getBase_ccy() {
        return base_ccy;
    }

    public void setBase_ccy(String base_ccy) {
        this.base_ccy = base_ccy;
    }

    public BigDecimal getBuy() {
        return buy;
    }

    public void setBuy(BigDecimal buy) {
        this.buy = buy;
    }

    public BigDecimal getSale() {
        return sale;
    }

    public void setSale(BigDecimal sale) {
        this.sale = sale;
    }
}
