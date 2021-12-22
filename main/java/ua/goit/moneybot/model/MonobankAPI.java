package ua.goit.moneybot.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class MonobankAPI implements BankAPI{

    private final HttpClient CLIENT = HttpClient.newHttpClient();
    private final Gson GSON = new Gson();
    private List<MonobankInfo> currency;

    public MonobankAPI() throws IOException, InterruptedException {
        currency = getActualCurrency();
    }

    @Override
    public BigDecimal getUsdBuy() {
        return null;
    }

    @Override
    public BigDecimal getUsdSell() {
        return null;
    }

    @Override
    public BigDecimal getEurBuy() {
        return null;
    }

    @Override
    public BigDecimal getEurSell() {
        return null;
    }

    @Override
    public BigDecimal getRubBuy() {
        return null;
    }

    @Override
    public BigDecimal getRubSell() {
        return null;
    }

    public List<MonobankInfo> getActualCurrency() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.monobank.ua/bank/currency"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        return  GSON.fromJson(response.body(), new TypeToken<List<MonobankInfo>>(){}.getType());
    }
}