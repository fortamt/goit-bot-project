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

public class NbuAPI{

    private static final String LINK = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private List<NbuInfo> currency;


    public NbuAPI() throws IOException, InterruptedException {
        currency = getActualCurrency();
    }

    public List<NbuInfo> getActualCurrency() throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LINK))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.statusCode());
//            System.out.println(currency.toString());
        return GSON.fromJson(response.body(), new TypeToken<List<NbuInfo>>() {}.getType());
    }

    public List<NbuInfo> getCurrency() {
        return currency;
    }



}
