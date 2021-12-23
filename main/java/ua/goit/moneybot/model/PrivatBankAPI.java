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

public class PrivatBankAPI implements BankAPI {

    private static final String LINK = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private List<PrivatBankInfo> currency;

    public PrivatBankAPI() throws IOException, InterruptedException {
        currency = getActualCurrency();
    }


    public  List<PrivatBankInfo> getActualCurrency() throws IOException, InterruptedException {

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LINK))
                    .GET()
                    .build();
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.statusCode());
//            System.out.println(currency.toString());
        return GSON.fromJson(response.body(), new TypeToken<List<PrivatBankInfo>>() {}.getType());

    }
    public List<PrivatBankInfo> getCurrency() {
        return currency;
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
}
