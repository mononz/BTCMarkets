package com.mononz.btcmarketssample;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import android.os.Bundle;
import android.widget.TextView;

import com.mononz.btcmarkets.BTCMarketsClient;
import com.mononz.btcmarkets.MarketCoin;
import com.mononz.btcmarkets.MarketCurrency;
import com.mononz.btcmarkets.MarketsTick;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    private BTCMarketsClient client;

    private CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);

        client = new BTCMarketsClient(this);
        client.setDebug(true);

        disposables.add(client.getMarketTick(MarketCoin.XRP, MarketCurrency.AUD)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<MarketsTick>() {
                    @Override
                    public void onNext(MarketsTick tick) {
                        text.setText(tick.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        text.setText(e.getMessage());
                    }

                    @Override
                    public void onComplete() { }
                }));
    }
}
