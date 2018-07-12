package io.ionic.ylnewapp.bean.products;

import java.util.List;

/**
 * Created by mogojing on 2018/6/27/0027.
 */

public class DIGDetailBean {

    /**
     * success : true
     * data : [{"global":true,"iconUrl":"http://192.168.123.207:8088/marketData/icon/eth.png","exchange":"全网","currencyPair":"ETH","prices":{"cny":2888.7321,"usd":438.004},"change":-2.82,"marketCap":2898.3646},{"global":true,"iconUrl":"http://192.168.123.207:8088/marketData/icon/btc.png","exchange":"全网","currencyPair":"BTC","prices":{"cny":40312.3495,"usd":6112.36},"change":-1.72,"marketCap":6900.7083}]
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
         * global : true
         * iconUrl : http://192.168.123.207:8088/marketData/icon/eth.png
         * exchange : 全网
         * currencyPair : ETH
         * prices : {"cny":2888.7321,"usd":438.004}
         * change : -2.82
         * marketCap : 2898.3646
         */

        private boolean global;
        private String iconUrl;
        private String exchange;
        private String currencyPair;
        private PricesBean prices;
        private double change;
        private double marketCap;

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

        public double getChange() {
            return change;
        }

        public void setChange(double change) {
            this.change = change;
        }

        public double getMarketCap() {
            return marketCap;
        }

        public void setMarketCap(double marketCap) {
            this.marketCap = marketCap;
        }

        public static class PricesBean {
            /**
             * cny : 2888.7321
             * usd : 438.004
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
