package ua.goit.moneybot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class Nbu {

    private static final String LINK = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static List<NbuInfo> currency = null;
    static CurrencyHolder holder = new CurrencyHolder();

    public static void main(String[] args) {
        Nbu.getRealRates();
        usd();
        System.out.println("To buy USD " + holder.getUsdRateBuy());
    }




    public static void getRealRates() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LINK))
                    .GET()
                    .build();
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            currency = GSON.fromJson(response.body(), new TypeToken<List<NbuInfo>>() {}.getType());
//            System.out.println(response.statusCode());
            System.out.println(currency.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void usd() {
        for (NbuInfo info : currency) {
            if (info.getCc().equalsIgnoreCase("usd")) {
                holder.setUsdRateBuy(info.getRate());
                break;
            }
        }
    }
    public static void eur() {
        for (NbuInfo info : currency) {
            if (info.getCc().equalsIgnoreCase("eur")) {
                holder.setEurRateBuy(info.getRate());
                break;
            }
        }
    }
    public static void rub() {
        for (NbuInfo info : currency) {
            if (info.getCc().equalsIgnoreCase("rur")) {
                holder.setRubRateBuy(info.getRate());
                break;
            }
        }
    }

}
