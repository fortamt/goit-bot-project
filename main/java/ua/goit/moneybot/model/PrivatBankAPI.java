package ua.goit.moneybot.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class PrivatBankAPI{

    private final HttpClient CLIENT = HttpClient.newHttpClient();
    private final Gson GSON = new Gson();

    public List<BankResponse> getActualCurrency() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<PrivatBankInfo> privat =  GSON.fromJson(response.body(), new TypeToken<List<PrivatBankInfo>>(){}.getType());
        PrivatBankInfo usd = getUsdCurrencyFromList(privat);
        PrivatBankInfo eur = getEurCurrencyFromList(privat);
        PrivatBankInfo rub = getRubCurrencyFromList(privat);
        List<BankResponse> bankResponses = new ArrayList<>();
        bankResponses.add(new BankResponse("PrivatBank", CurrencyEnum.USD.getCodeString(),
                new BigDecimal(String.valueOf(usd.getBuy())), new BigDecimal(String.valueOf(usd.getSale()))));
        bankResponses.add(new BankResponse("PrivatBank", CurrencyEnum.EUR.getCodeString(),
                new BigDecimal(String.valueOf(eur.getBuy())), new BigDecimal(String.valueOf(eur.getSale()))));
        bankResponses.add(new BankResponse("PrivatBank", CurrencyEnum.RUB.getCodeString(),
                new BigDecimal(String.valueOf(rub.getBuy())), new BigDecimal(String.valueOf(rub.getSale()))));
        return bankResponses;

    }

    private PrivatBankInfo getUsdCurrencyFromList(List<PrivatBankInfo> privat) {
        return privat.stream()
                .filter(e -> CurrencyEnum.USD.getCodeString().equals(e.getCcy()))
                .findFirst()
                .orElse(null);
    }

    private PrivatBankInfo getEurCurrencyFromList(List<PrivatBankInfo> privat) {
        return privat.stream()
                .filter(e -> CurrencyEnum.EUR.getCodeString().equals(e.getCcy()))
                .findFirst()
                .orElse(null);
    }

    private PrivatBankInfo getRubCurrencyFromList(List<PrivatBankInfo> privat) {
        return privat.stream()
                .filter(e -> "RUR".equals(e.getCcy()))
                .findFirst()
                .orElse(null);
    }


}
