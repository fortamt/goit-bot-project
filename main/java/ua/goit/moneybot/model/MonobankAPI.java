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

public class MonobankAPI{

    private final HttpClient CLIENT = HttpClient.newHttpClient();
    private final Gson GSON = new Gson();


    public List<BankResponse> getActualCurrency() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.monobank.ua/bank/currency"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<MonobankInfo> mono =  GSON.fromJson(response.body(), new TypeToken<List<MonobankInfo>>(){}.getType());
        MonobankInfo usd = getUsdCurrencyFromList(mono);
        MonobankInfo eur = getEurCurrencyFromList(mono);
        MonobankInfo rub = getRubCurrencyFromList(mono);
        List<BankResponse> bankResponses = new ArrayList<>();
        bankResponses.add(new BankResponse("Monobank", CurrencyEnum.USD.getCodeString(),
                new BigDecimal(String.valueOf(usd.getRateBuy())), new BigDecimal(String.valueOf(usd.getRateSell()))));
        bankResponses.add(new BankResponse("Monobank", CurrencyEnum.EUR.getCodeString(),
                new BigDecimal(String.valueOf(eur.getRateBuy())), new BigDecimal(String.valueOf(eur.getRateSell()))));
        bankResponses.add(new BankResponse("Monobank", CurrencyEnum.RUB.getCodeString(),
                new BigDecimal(String.valueOf(rub.getRateBuy())), new BigDecimal(String.valueOf(rub.getRateSell()))));
        return bankResponses;

    }

    private MonobankInfo getUsdCurrencyFromList(List<MonobankInfo> mono) {
        return mono.stream()
                .filter(e -> CurrencyEnum.USD.getCodeInt() == e.getCurrencyCodeA())
                .findFirst()
                .orElse(null);
    }

    private MonobankInfo getEurCurrencyFromList(List<MonobankInfo> mono) {
        return mono.stream()
                .filter(e -> CurrencyEnum.EUR.getCodeInt() == e.getCurrencyCodeA())
                .findFirst()
                .orElse(null);
    }

    private MonobankInfo getRubCurrencyFromList(List<MonobankInfo> mono) {
        return mono.stream()
                .filter(e -> CurrencyEnum.RUB.getCodeInt() == e.getCurrencyCodeA())
                .findFirst()
                .orElse(null);
    }


}