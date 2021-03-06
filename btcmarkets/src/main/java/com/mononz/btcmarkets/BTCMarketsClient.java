package com.mononz.btcmarkets;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import io.reactivex.Observable;

public class BTCMarketsClient {

    private String apiKey;
    private String privateKey;

    private boolean DEBUG = false;

    private static final String BASE_URL = "https://api.btcmarkets.net";

    public BTCMarketsClient(Context context) {
        AndroidNetworking.initialize(context);
    }

    public BTCMarketsClient(Context context, String apiKey, String privateKey) {
        this.apiKey = apiKey;
        this.privateKey = privateKey;
        AndroidNetworking.initialize(context);

//        TrueTimeRx.build()
//                .initializeRx("time.google.com")
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<Date>() {
//                    @Override
//                    public void accept(Date date) {
//                        debug("TrueTime was initialized and we have a time: " + date);
//                    }
//                });
    }

    public void setDebug(boolean debug) {
        this.DEBUG = debug;
    }

    private void debug(String msg) {
        if (DEBUG) {
            Log.d("BTCMarkets", msg);
        }
    }

    public Observable<MarketsTick> getMarketTick(MarketCoin coin, MarketCurrency currency) {
        return Rx2AndroidNetworking.get("{url}/market/{coin}/{currency}/tick")
                .addPathParameter("url", BASE_URL)
                .addPathParameter("coin", coin.name())
                .addPathParameter("currency", currency.name())
                .build()
                .getObjectObservable(MarketsTick.class);
    }
}
