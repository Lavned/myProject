package io.ionic.ylnewapp.bean.market;

import java.util.List;

/**
 * Created by mogojing on 2018/6/21/0021.
 */

public class SearchBean {

    /**
     * success : true
     * data : [{"exchangeName":"Bittrex","currencyPair":"BTC/USDT","global":false},{"exchangeName":"全网","currencyPair":"BTC","global":true}]
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
         * exchangeName : Bittrex
         * currencyPair : BTC/USDT
         * global : false
         */

        private String exchangeName;
        private String currencyPair;
        private boolean global;

        public String getExchangeName() {
            return exchangeName;
        }

        public void setExchangeName(String exchangeName) {
            this.exchangeName = exchangeName;
        }

        public String getCurrencyPair() {
            return currencyPair;
        }

        public void setCurrencyPair(String currencyPair) {
            this.currencyPair = currencyPair;
        }

        public boolean isGlobal() {
            return global;
        }

        public void setGlobal(boolean global) {
            this.global = global;
        }
    }
}
