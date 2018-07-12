package io.ionic.ylnewapp.bean.market;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mogojing on 2018/6/28/0028.
 */

public class OtherExchangeBBean {

    /**
     * success : true
     * data : [{"global":false,"iconUrl":"http://192.168.123.207:8088/marketData/icon/btc.png","exchange":"Bittrex","currencyPair":"BTC/USDT","prices":{"cny":40891.956,"usd":6190.6635},"change":0,"marketCap":0}]
     * message : 处理成功！
     */

    private boolean success;
    private String message;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * global : false
         * iconUrl : http://192.168.123.207:8088/marketData/icon/btc.png
         * exchange : Bittrex
         * currencyPair : BTC/USDT
         * prices : {"cny":40891.956,"usd":6190.6635}
         * change : 0
         * marketCap : 0
         */

        private boolean global;
        private String iconUrl;
        private String exchange;
        private String currencyPair;
        private PricesBean prices;
        private BigDecimal change;
        private BigDecimal marketCap;

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

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public String getCurrencyPair() {
            return currencyPair;
        }

        public void setCurrencyPair(String currencyPair) {
            this.currencyPair = currencyPair;
        }

        public PricesBean getPrices() {
            return prices;
        }

        public void setPrices(PricesBean prices) {
            this.prices = prices;
        }

        public BigDecimal getChange() {
            return change;
        }

        public void setChange(BigDecimal change) {
            this.change = change;
        }

        public BigDecimal getMarketCap() {
            return marketCap;
        }

        public void setMarketCap(BigDecimal marketCap) {
            this.marketCap = marketCap;
        }

        public static class PricesBean {
            /**
             * cny : 40891.956
             * usd : 6190.6635
             */

            private double cny;
            private double usd;

            public double getCny() {
                return cny;
            }

            public void setCny(double cny) {
                this.cny = cny;
            }

            public double getUsd() {
                return usd;
            }

            public void setUsd(double usd) {
                this.usd = usd;
            }
        }
    }
}
