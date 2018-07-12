package io.ionic.ylnewapp.bean.market;

import java.math.BigDecimal;

/**
 * Created by mogojing on 2018/6/27/0027.
 */

public class MarketSummaryBean {

    /**
     * success : true
     * data : {"desc":"Bitcoin","circulatingSupply":1711.81,"totalSupply":1711.81,"marketCap":6.886618686E7}
     * message : 处理成功！
     */

    private boolean success;
    private DataBean data;
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * desc : Bitcoin
         * circulatingSupply : 1711.81
         * totalSupply : 1711.81
         * marketCap : 6.886618686E7
         */

        private String desc;
        private BigDecimal circulatingSupply;
        private BigDecimal totalSupply;
        private BigDecimal marketCap;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public BigDecimal getCirculatingSupply() {
            return circulatingSupply;
        }

        public void setCirculatingSupply(BigDecimal circulatingSupply) {
            this.circulatingSupply = circulatingSupply;
        }

        public BigDecimal getTotalSupply() {
            return totalSupply;
        }

        public void setTotalSupply(BigDecimal totalSupply) {
            this.totalSupply = totalSupply;
        }

        public BigDecimal getMarketCap() {
            return marketCap;
        }

        public void setMarketCap(BigDecimal marketCap) {
            this.marketCap = marketCap;
        }
    }
}
