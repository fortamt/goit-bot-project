package ua.goit.moneybot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ua.goit.moneybot.privatbank_api.PrivatBankInfo;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class PrivatBank {

    private static final String LINK = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
    private static final HttpClient CLIENT = HttpClient.newHttpClient();
    private static final Gson GSON = new Gson();
    private static List<PrivatBankInfo> currency = null;
    static CurrencyHolder holder = new CurrencyHolder();

    public static void main(String[] args) {
        PrivatBank.getRealRates();
        usd();
        System.out.println("To buy USD " + holder.getUsdRateBuy() + "\nTo Sell USD " + holder.getUsdRateSell());
    }




    public static void getRealRates() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(LINK))
                    .GET()
                    .build();
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            currency = GSON.fromJson(response.body(), new TypeToken<List<PrivatBankInfo>>() {}.getType());
//            System.out.println(response.statusCode());
//            System.out.println(currency.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void usd() {
        for (PrivatBankInfo info : currency) {
            if (info.getCcy().equalsIgnoreCase("usd")) {
                holder.setUsdRateBuy(info.getBuy());
                holder.setUsdRateSell(info.getSale());
                break;
            }
        }
    }
    public static void eur() {
        for (PrivatBankInfo info : currency) {
            if (info.getCcy().equalsIgnoreCase("eur")) {
                holder.setEurRateBuy(info.getBuy());
                holder.setEurRateSell(info.getSale());
                break;
            }
        }
    }
    public static void rub() {
        for (PrivatBankInfo info : currency) {
            if (info.getCcy().equalsIgnoreCase("rur")) {
                holder.setRubRateBuy(info.getBuy());
                holder.setRubRateSell(info.getSale());
                break;
            }
        }
    }

}
