package ua.goit.moneybot.api;

import com.google.gson.JsonArray;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NbuApi {
    private static final String LINK_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";


    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LINK_URL))
                .build();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                //.thenAccept(System.out::println)
                .thenApply(NbuApi::usd)
                .join();
    }

    public static String usd(String responseBody) {
        JSONArray currency = new JSONArray(responseBody);
        JSONObject currencyObject = null;
        for (int i = 0; i < currency.length(); i++) {
            currencyObject = currency.getJSONObject(i);
            if (currencyObject.getString("cc").equalsIgnoreCase("USD")) {
                System.out.println(currencyObject.getBigDecimal("rate"));
            }
        }
        return null;
    }

}
