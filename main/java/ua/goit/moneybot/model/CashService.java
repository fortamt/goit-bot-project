package ua.goit.moneybot.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CashService {
    private CopyOnWriteArrayList<BankResponse> monobankResponse = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<BankResponse> privatBankResponse = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<BankResponse> nbuResponse = new CopyOnWriteArrayList<>();
    ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(3);
    private boolean monobankRateIsValid = false;
    private boolean privatbankRateIsValid = false;
    private boolean nbuRateIsValid = false;

    public void monobankRateFlush() {
        monobankResponse.clear();
        monobankRateIsValid = false;
    }

    public void privatBankRateFlush() {
        privatBankResponse.clear();
        privatbankRateIsValid = false;
    }

    public void nbuRateFlush() {
        nbuResponse.clear();
        nbuRateIsValid = false;
    }

    public List<BankResponse> getCashedMonobankCurrency() {
        MonobankAPI monobankAPI = new MonobankAPI();
        if (monobankRateIsValid == false | monobankResponse.size() == 0) {
            monobankRateIsValid = true;
            try {
                monobankResponse.addAll(monobankAPI.getActualCurrency());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Runnable monobankRateFlushRunnable = () -> monobankRateFlush();
            if (executor.getActiveCount() != 0) {
                executor.shutdownNow();
            }
            executor.schedule(monobankRateFlushRunnable, 5, TimeUnit.MINUTES);
        }
        ArrayList<BankResponse> bankResponse = new ArrayList<>();
        bankResponse.addAll(monobankResponse);
        return bankResponse;
    }

    public List<BankResponse> getCashedPrivatBankCurrency() {
        PrivatBankAPI privatBankAPI = new PrivatBankAPI();
        if (privatbankRateIsValid == false | privatBankResponse.size() == 0) {
            privatbankRateIsValid = true;
            try {
                privatBankResponse.addAll(privatBankAPI.getActualCurrency());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Runnable privatBankRateFlushRunnable = () -> privatBankRateFlush();
            if (executor.getActiveCount() != 0) {
                executor.shutdownNow();
            }
            executor.schedule(privatBankRateFlushRunnable, 5, TimeUnit.MINUTES);
        }
        ArrayList<BankResponse> bankResponse = new ArrayList<>();
        bankResponse.addAll(privatBankResponse);
        return bankResponse;
    }

    public List<BankResponse> getCashedNBUCurrency() {
        NbuAPI nbuAPI = new NbuAPI();
        if (nbuRateIsValid == false | nbuResponse.size() == 0) {
            nbuRateIsValid = true;
            try {
                nbuResponse.addAll(nbuAPI.getActualCurrency());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Runnable nbuRateFlushRunnable = () -> nbuRateFlush();
            if (executor.getActiveCount() != 0) {
                executor.shutdownNow();
            }
            executor.schedule(nbuRateFlushRunnable, 5, TimeUnit.MINUTES);
        }
        ArrayList<BankResponse> bankResponse = new ArrayList<>();
        bankResponse.addAll(nbuResponse);
        return bankResponse;
    }
}
