package io.ionic.ylnewapp.bean.market;

import java.util.List;

/**
 * Created by mogojing on 2018/6/21/0021.
 */

public class ToMarketBean {

    /**
     * status : 200
     * body : [{"_id":"5b2b0a248d44f50eee65991a","exchangeName":"全网","currencyPair":"ETF","global":true},{"_id":"5b2b087ad834400d2a6da327","exchangeName":"全网","currencyPair":"ETF","global":true},{"_id":"5b2b0873d834400d2a6da326","exchangeName":"火币","currencyPair":"ETF","global":false}]
     */

    private int status;
    private List<BodyBean> body;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<BodyBean> getBody() {
        return body;
    }

    public void setBody(List<BodyBean> body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * _id : 5b2b0a248d44f50eee65991a
         * exchangeName : 全网
         * currencyPair : ETF
         * global : true
         */

        private String _id;
        private String exchangeName;
        private String currencyPair;
        private boolean global;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

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
