package ua.goit.moneybot;

public enum BankNameEnum {
    NBU("НБУ"),
    PRIVATBANK("ПриватБанк"),
    MONOBANK("Монобанк");

    private String bankName;

    BankNameEnum(String bankName) {
        this.bankName = bankName;
    }

    public String getBankName(){
        return bankName;
    }
}
