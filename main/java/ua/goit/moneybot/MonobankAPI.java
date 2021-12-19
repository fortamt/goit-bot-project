package ua.goit.moneybot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class MonobankAPI {
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static List<CurrencyInfo> currency = null;
    static CurrencyHolder currencyHolder = new CurrencyHolder();


    public static void main(String[] args) throws IOException, InterruptedException {

        MonobankAPI.getActualCurrency();
        usd();
        System.out.println("курс продажи USD: " + currencyHolder.getUsdRateSell());
        System.out.println("курс покупки USD: " + currencyHolder.getUsdRateBuy());

        rub();
        System.out.println("курс продажи RUB: " + currencyHolder.getRubRateSell());
        System.out.println("курс покупки RUB: " + currencyHolder.getRubRateBuy());

        eur();
        System.out.println("курс продажи EUR: " + currencyHolder.getEurRateSell());
        System.out.println("курс покупки EUR: " + currencyHolder.getEurRateBuy());
    }

    public static void usd(){
        for(CurrencyInfo money : currency){
            if(money.getCurrencyCodeA() == 840){
                currencyHolder.setUsdRateSell(money.getRateSell());
                currencyHolder.setUsdRateBuy(money.getRateBuy());
                break;
            }
        }
    }

    public static void rub(){
        for(CurrencyInfo money : currency){
            if(money.getCurrencyCodeA() == 643){
                currencyHolder.setRubRateSell(money.getRateSell());
                currencyHolder.setRubRateBuy(money.getRateBuy());
                break;
            }
        }
    }

    public static void eur(){
        for(CurrencyInfo money : currency){
            if(money.getCurrencyCodeA() == 978){
                currencyHolder.setEurRateSell(money.getRateSell());
                currencyHolder.setEurRateBuy(money.getRateBuy());
                break;
            }
        }
    }

    public static void getActualCurrency() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.monobank.ua/bank/currency"))
                .GET()
                .build();
        HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
        currency =  GSON.fromJson(response.body(), new TypeToken<List<CurrencyInfo>>(){}.getType());
    }
}