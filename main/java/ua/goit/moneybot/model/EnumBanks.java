package ua.goit.moneybot.model;

public enum EnumBanks {

    USD(840, "USD"),
    EUR(978, "EUR"),
    RUB(643, "RUB");


    private int codeInt;
    private String codeString;

    EnumBanks(int codeInt, String codeString) {
        this.codeInt = codeInt;
        this.codeString = codeString;
    }

    public int getCodeInt() {
        return codeInt;
    }

    public String getCodeString() {
        return codeString;
    }
}
