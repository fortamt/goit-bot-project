package ua.goit.moneybot.model;

public enum EnumBanks {

    MONOUSD(840),
    MONOEUR(978),
    MONORUB(643),

    NBUUSD(840),
    NBUEUR(978),
    NBURUB(643),

    PBUSD("USD"),
    PBEUR("EUR"),
    PBRUB("RUR");

    private int codeInt;
    private String codeString;

    EnumBanks(int codeInt) {
        this.codeInt = codeInt;
    }

    EnumBanks(String codeString) {
        this.codeString = codeString;
    }

    public int getCodeInt() {
        return codeInt;
    }

    public String getCodeString() {
        return codeString;
    }
}
