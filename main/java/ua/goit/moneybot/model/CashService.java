package ua.goit.moneybot.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CashService {
    private List<BankResponse> monobankResponse = new ArrayList<>();
    private List<BankResponse> privatBankResponse = new ArrayList<>();
    private List<BankResponse> nbuResponse = new ArrayList<>();
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
        if (monobankRateIsValid == false) {
            monobankRateIsValid = true;
            try {
                monobankResponse.addAll(monobankAPI.getActualCurrency());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Runnable monobankRateFlushRunnable = () -> monobankRateFlush();
        executor.schedule(monobankRateFlushRunnable, 5, TimeUnit.SECONDS);
        return monobankResponse;
    }

    public List<BankResponse> getCashedPrivatBankCurrency() {
        MonobankAPI privatBankAPI = new MonobankAPI();
        if (privatbankRateIsValid == false) {
            privatbankRateIsValid = true;
            try {
                privatBankResponse.addAll(privatBankAPI.getActualCurrency());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Runnable privatBankRateFlushRunnable = () -> privatBankRateFlush();
        executor.schedule(privatBankRateFlushRunnable, 5, TimeUnit.SECONDS);
        return privatBankResponse;
    }

    public List<BankResponse> getCashedNBUCurrency() {
        MonobankAPI nbuAPI = new MonobankAPI();
        if (nbuRateIsValid == false) {
            nbuRateIsValid = true;
            try {
                nbuResponse.addAll(nbuAPI.getActualCurrency());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Runnable nbuRateFlushRunnable = () -> nbuRateFlush();
        executor.schedule(nbuRateFlushRunnable, 5, TimeUnit.SECONDS);
        return nbuResponse;
    }
}
