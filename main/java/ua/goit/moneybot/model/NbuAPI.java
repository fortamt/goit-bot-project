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

public class NbuAPI {

    private final HttpClient CLIENT = HttpClient.newHttpClient();
    private final Gson GSON = new Gson();

    public List<BankResponse> getActualCurrency() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        List<NbuInfo> nbuInfos = GSON.fromJson(response.body(), new TypeToken<List<NbuInfo>>() {
        }.getType());
        NbuInfo usd = getUsdCurrencyFromList(nbuInfos);
        NbuInfo eur = getEurCurrencyFromList(nbuInfos);
        NbuInfo rub = getRubCurrencyFromList(nbuInfos);
        List<BankResponse> bankResponses = new ArrayList<>();
        bankResponses.add(new BankResponse("NBU", CurrencyEnum.USD.getCodeString(),
                new BigDecimal(String.valueOf(usd.getRate())), new BigDecimal(String.valueOf(usd.getRate()))));
        bankResponses.add(new BankResponse("NBU", CurrencyEnum.EUR.getCodeString(),
                new BigDecimal(String.valueOf(eur.getRate())), new BigDecimal(String.valueOf(eur.getRate()))));
        bankResponses.add(new BankResponse("NBU", CurrencyEnum.RUB.getCodeString(),
                new BigDecimal(String.valueOf(rub.getRate())), new BigDecimal(String.valueOf(rub.getRate()))));
        return bankResponses;
    }

    private NbuInfo getUsdCurrencyFromList(List<NbuInfo> nbuInfos) {
        return nbuInfos.stream()
                .filter(e -> CurrencyEnum.USD.getCodeInt() == e.getR030())
                .findFirst()
                .orElse(null);
    }

    private NbuInfo getEurCurrencyFromList(List<NbuInfo> nbuInfos) {
        return nbuInfos.stream()
                .filter(e -> CurrencyEnum.EUR.getCodeInt() == e.getR030())
                .findFirst()
                .orElse(null);
    }

    private NbuInfo getRubCurrencyFromList(List<NbuInfo> nbuInfos) {
        return nbuInfos.stream()
                .filter(e -> CurrencyEnum.RUB.getCodeInt() == e.getR030())
                .findFirst()
                .orElse(null);
    }
}
