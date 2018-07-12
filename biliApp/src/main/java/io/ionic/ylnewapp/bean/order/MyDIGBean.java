package io.ionic.ylnewapp.bean.order;

import java.util.List;

/**
 * Created by mogojing on 2018/6/27/0027.
 */

public class MyDIGBean {


    /**
     * status : 200
     * body : [{"type":"eth","total":0,"lists":[{"orderid":"5720180626185742","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.00枚eth"},{"key":"到期收益","val":"0.01枚eth"}],"key":"eth","payAmount":"1eth","week":"90天","created":"2018.06.26 18:57","open":"2018.09.24","status":"待付款","btn":["1","2"]},{"orderid":"4020180620104036","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.01枚eth"},{"key":"到期收益","val":"0.13枚eth"}],"key":"eth","payAmount":"11eth","week":"90天","created":"2018.06.20 10:40","open":"2018.09.18","status":"已付款","btn":["7"]},{"orderid":"2120180620102117","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.02枚eth"},{"key":"到期收益","val":"0.26枚eth"}],"key":"eth","payAmount":"22eth","week":"90天","created":"2018.06.20 10:21","open":"2018.09.18","status":"已付款","btn":["7"]},{"orderid":"1220180619191253","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.01枚eth"},{"key":"到期收益","val":"0.14枚eth"}],"key":"eth","payAmount":"12eth","week":"90天","created":"2018.06.19 19:12","open":"2018.09.17","status":"已付款","btn":["7"]},{"orderid":"520180619190527","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.00枚eth"},{"key":"到期收益","val":"0.01枚eth"}],"key":"eth","payAmount":"1eth","week":"90天","created":"2018.06.19 19:05","open":"2018.09.17","status":"已付款","btn":["7"]}]},{"type":"btc","total":13,"mount":1,"lists":[{"orderid":"4920180620094925","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.01枚btc"},{"key":"到期收益","val":"0.14枚btc"}],"key":"btc","payAmount":"12btc","week":"90天","created":"2018.06.20 09:49","open":"2018.09.18","status":"已付款","btn":["7"]}]},{"type":"bncn","total":124999,"mount":22999,"lists":[{"orderid":"3920180619093957","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"92.05枚bncn"},{"key":"到期收益","val":"1183.56枚bncn"}],"key":"bncn","payAmount":"100000bncn","week":"90天","created":"2018.06.19 09:39","open":"2018.09.17","status":"已付款","btn":["7"]},{"orderid":"4420180521164402","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"1.84枚bncn"},{"key":"到期收益","val":"23.67枚bncn"}],"key":"bncn","payAmount":"2000bncn","week":"90天","created":"2018.05.21 16:44","open":"2018.08.19","status":"已付款","btn":["7"]}]}]
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
         * type : eth
         * total : 0
         * lists : [{"orderid":"5720180626185742","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.00枚eth"},{"key":"到期收益","val":"0.01枚eth"}],"key":"eth","payAmount":"1eth","week":"90天","created":"2018.06.26 18:57","open":"2018.09.24","status":"待付款","btn":["1","2"]},{"orderid":"4020180620104036","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.01枚eth"},{"key":"到期收益","val":"0.13枚eth"}],"key":"eth","payAmount":"11eth","week":"90天","created":"2018.06.20 10:40","open":"2018.09.18","status":"已付款","btn":["7"]},{"orderid":"2120180620102117","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.02枚eth"},{"key":"到期收益","val":"0.26枚eth"}],"key":"eth","payAmount":"22eth","week":"90天","created":"2018.06.20 10:21","open":"2018.09.18","status":"已付款","btn":["7"]},{"orderid":"1220180619191253","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.01枚eth"},{"key":"到期收益","val":"0.14枚eth"}],"key":"eth","payAmount":"12eth","week":"90天","created":"2018.06.19 19:12","open":"2018.09.17","status":"已付款","btn":["7"]},{"orderid":"520180619190527","name":"数字资产一季","rate":[{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.00枚eth"},{"key":"到期收益","val":"0.01枚eth"}],"key":"eth","payAmount":"1eth","week":"90天","created":"2018.06.19 19:05","open":"2018.09.17","status":"已付款","btn":["7"]}]
         * mount : 1
         */

        private String type;
        private int total;
        private int mount;
        private List<ListsBean> lists;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getMount() {
            return mount;
        }

        public void setMount(int mount) {
            this.mount = mount;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * orderid : 5720180626185742
             * name : 数字资产一季
             * rate : [{"key":"年化收益率","val":"4.8"},{"key":"累计收益","val":"0.00枚eth"},{"key":"到期收益","val":"0.01枚eth"}]
             * key : eth
             * payAmount : 1eth
             * week : 90天
             * created : 2018.06.26 18:57
             * open : 2018.09.24
             * status : 待付款
             * btn : ["1","2"]
             */

            private String orderid;
            private String name;
            private String key;
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

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
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
                 * val : 4.8
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
}
