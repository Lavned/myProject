package io.ionic.ylnewapp.bean.market;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mogojing on 2018/6/21/0021.
 */

public class ZxDetailBean {


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
         * exchange : Bittrex
         * currencyPair : BTC/USDT
         * open : 6786
         * close : 6785.1
         * lowest : 6785.1
         * highest : 6786
         * timestamp : 1528683002549
         * BigDecimalervalDate : 1528682940000
         */

        private String exchange;
        private String currencyPair;
        private BigDecimal open;
        private BigDecimal close;
        private BigDecimal lowest;
        private BigDecimal highest;
        private long timestamp;
        private long intervalDate;

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

        public BigDecimal getOpen() {
            return open;
        }

        public void setOpen(BigDecimal open) {
            this.open = open;
        }

        public BigDecimal getClose() {
            return close;
        }

        public void setClose(BigDecimal close) {
            this.close = close;
        }

        public BigDecimal getLowest() {
            return lowest;
        }

        public void setLowest(BigDecimal lowest) {
            this.lowest = lowest;
        }

        public BigDecimal getHighest() {
            return highest;
        }

        public void setHighest(BigDecimal highest) {
            this.highest = highest;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public long getIntervalDate() {
            return intervalDate;
        }

        public void setIntervalDate(long intervalDate) {
            this.intervalDate = intervalDate;
        }
    }
}
