package io.ionic.ylnewapp.bean.market;

import java.math.BigDecimal;

/**
 * Created by mogojing on 2018/6/20/0020.
 */

public class MarketBean {

    /**
     * change : -1.83
     * currencyPair : BTC
     * exchange : 全网
     * global : true
     * iconUrl : http://192.168.123.207:8088/icon/BTC.png
     * marketCap : 734430887261
     * prices : {"cny":42940.1045382031,"usd":6621.45}
     */

    private BigDecimal change;
    private String currencyPair;
    private String exchange;
    private boolean global;
    private String iconUrl;
    private BigDecimal marketCap;
    private PricesBean prices;

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public BigDecimal getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(BigDecimal marketCap) {
        this.marketCap = marketCap;
    }

    public PricesBean getPrices() {
        return prices;
    }

    public void setPrices(PricesBean prices) {
        this.prices = prices;
    }

    public static class PricesBean {
        /**
         * cny : 42940.1045382031
         * usd : 6621.45
         */

        private BigDecimal cny;
        private BigDecimal usd;

        public BigDecimal getCny() {
            return cny;
        }

        public void setCny(BigDecimal cny) {
            this.cny = cny;
        }

        public BigDecimal getUsd() {
            return usd;
        }

        public void setUsd(BigDecimal usd) {
            this.usd = usd;
        }
    }
}
