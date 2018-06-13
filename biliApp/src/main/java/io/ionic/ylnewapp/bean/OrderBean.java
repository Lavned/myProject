package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/6/12/0012.
 */

public class OrderBean {


    /**
     * status : 200
     * body : [{"orderid":"020180521170036","name":"比特币套利基金一季","rate":[{"key":"年化收益率","val":"16.0"},{"key":"累计收益","val":"￥19.29"},{"key":"到期收益","val":"￥78.90"}],"payAmount":"￥2000","week":"90天","created":"2018.05.21 17:00","open":"2018.08.19","status":"已付款","btn":["申请赎回"]},{"orderid":"4320180521154300","name":"比特币套利基金一季","rate":[{"key":"年化收益率","val":"16.0"},{"key":"累计收益","val":"￥19.29"},{"key":"到期收益","val":"￥78.90"}],"payAmount":"￥2000","week":"90天","created":"2018.05.21 15:43","open":"2018.08.19","status":"已付款","btn":["申请赎回"]},{"orderid":"2520180521152540","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.01枚etf"},{"key":"到期收益","val":"0.02枚etf"}],"payAmount":"￥2","week":"90天","created":"2018.05.21 15:25","open":"2018.08.19","status":"已付款","btn":["申请赎回"]},{"orderid":"5020180521105054","name":"比特币套利基金新月","rate":[{"key":"年化收益率","val":"6.4"},{"key":"累计收益","val":"￥23.15"},{"key":"到期收益","val":"￥31.56"}],"payAmount":"￥6000","week":"30天","created":"2018.05.21 10:50","open":"2018.06.20","status":"已付款","btn":["申请赎回"]},{"orderid":"2920180517142930","name":"马六甲比特币一号【一期】","rate":[{"key":"历史收益率","val":"105.84%"},{"key":"基金净值","val":"12350.40"}],"payAmount":"￥6000","week":"365天","created":"2018.05.17 14:29","open":"2019.05.17","status":"已付款","btn":["我要挂单","继续投资","申请赎回"]},{"orderid":"1420180508171423","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥25.89"},{"key":"到期收益","val":"￥5.18"}],"payAmount":"￥5000","week":"7天","created":"2018.05.08 17:14","open":"2018.05.15","status":"已付款","btn":["申请赎回"]},{"orderid":"2420180508142427","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥5.18"},{"key":"到期收益","val":"￥1.04"}],"payAmount":"￥1000","week":"7天","created":"2018.05.08 14:24","open":"2018.05.15","status":"已付款","btn":["申请赎回"]},{"orderid":"4320180416184321","name":"BTC指数基金","rate":[{"key":"收益率","val":"-0.17%"},{"key":"基金份额","val":"0.12"}],"payAmount":"￥6000","week":"365天","created":"2018.04.16 18:43","open":"2019.04.16","status":"已付款","btn":["继续投资","申请赎回"]},{"orderid":"02018040416003100031","name":"马六甲比特币一号【十二期】","rate":[{"key":"历史收益率","val":"43.75%"},{"key":"基金净值","val":"7187.50"}],"payAmount":"￥5000","week":"365天","created":"2018.04.04 16:00","open":"2019.04.04","status":"已付款","btn":["我要挂单","继续投资","申请赎回"]}]
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
         * orderid : 020180521170036
         * name : 比特币套利基金一季
         * rate : [{"key":"年化收益率","val":"16.0"},{"key":"累计收益","val":"￥19.29"},{"key":"到期收益","val":"￥78.90"}]
         * payAmount : ￥2000
         * week : 90天
         * created : 2018.05.21 17:00
         * open : 2018.08.19
         * status : 已付款
         * btn : ["申请赎回"]
         */

        private String orderid;
        private String name;
        private String payAmount;
        private String week;
        private String created;
        private String open;
        private String status;
        private List<RateBean> rate;
        private List<String> btn;

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(String payAmount) {
            this.payAmount = payAmount;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getOpen() {
            return open;
        }

        public void setOpen(String open) {
            this.open = open;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<RateBean> getRate() {
            return rate;
        }

        public void setRate(List<RateBean> rate) {
            this.rate = rate;
        }

        public List<String> getBtn() {
            return btn;
        }

        public void setBtn(List<String> btn) {
            this.btn = btn;
        }

        public static class RateBean {
            /**
             * key : 年化收益率
             * val : 16.0
             */

            private String key;
            private String val;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getVal() {
                return val;
            }

            public void setVal(String val) {
                this.val = val;
            }
        }
    }
}
