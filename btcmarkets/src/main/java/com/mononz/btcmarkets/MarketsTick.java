package com.mononz.btcmarkets;

public class MarketsTick {

    private Double bestAsk;
    private Double lastPrice;
    private String currency;
    private String instrument;
    private Long timestamp;
    private Double volume24h;

    public Double getBestAsk() {
        return bestAsk;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public String getInstrument() {
        return instrument;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Double getVolume24h() {
        return volume24h;
    }

    @Override
    public String toString() {
        return "MarketsTick{" +
                "bestAsk=" + bestAsk +
                ", lastPrice=" + lastPrice +
                ", currency='" + currency + '\'' +
                ", instrument='" + instrument + '\'' +
                ", timestamp=" + timestamp +
                ", volume24h=" + volume24h +
                '}';
    }
}
