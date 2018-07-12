package io.ionic.ylnewapp.bean.order;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mogojing on 2018/6/26/0026.
 */

public class AllOrderBean {


    /**
     * status : 200
     * body : {"orders":[{"_id":"0","count":67188,"type":"OTC套利基金","key":"OTC","color":"#e7d878","list":[{"orderid":"2520180621152526","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥1.18"},{"key":"到期收益","val":"￥2.07"}],"payAmount":"￥2000","week":"7天","created":"2018.06.21 15:25","open":"2018.06.28","status":"已付款","btn":["7"]},{"orderid":"1320180620151314","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥1.48"},{"key":"到期收益","val":"￥2.07"}],"payAmount":"￥2000","week":"7天","created":"2018.06.20 15:13","open":"2018.06.27","status":"已付款","btn":["7"]},{"orderid":"5320180620105306","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥0.82"},{"key":"到期收益","val":"￥1.15"}],"payAmount":"￥1111","week":"7天","created":"2018.06.20 10:53","open":"2018.06.27","status":"已付款","btn":["7"]},{"orderid":"4420180619174425","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥22.42"},{"key":"到期收益","val":"￥26.15"}],"payAmount":"￥25255","week":"7天","created":"2018.06.19 17:44","open":"2018.06.26","status":"已付款","btn":["7"]},{"orderid":"2320180619172317","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥0.89"},{"key":"到期收益","val":"￥1.04"}],"payAmount":"￥1000","week":"7天","created":"2018.06.19 17:23","open":"2018.06.26","status":"已付款","btn":["7"]}],"size":20},{"_id":"3","count":2000,"type":"智能投顾","key":"ICO","color":"#f4a277","list":[{"orderid":"2520180621152526","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥1.18"},{"key":"到期收益","val":"￥2.07"}],"payAmount":"￥2000","week":"7天","created":"2018.06.21 15:25","open":"2018.06.28","status":"已付款","btn":["7"]},{"orderid":"1320180620151314","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥1.48"},{"key":"到期收益","val":"￥2.07"}],"payAmount":"￥2000","week":"7天","created":"2018.06.20 15:13","open":"2018.06.27","status":"已付款","btn":["7"]},{"orderid":"5320180620105306","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥0.82"},{"key":"到期收益","val":"￥1.15"}],"payAmount":"￥1111","week":"7天","created":"2018.06.20 10:53","open":"2018.06.27","status":"已付款","btn":["7"]},{"orderid":"4420180619174425","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥22.42"},{"key":"到期收益","val":"￥26.15"}],"payAmount":"￥25255","week":"7天","created":"2018.06.19 17:44","open":"2018.06.26","status":"已付款","btn":["7"]},{"orderid":"2320180619172317","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥0.89"},{"key":"到期收益","val":"￥1.04"}],"payAmount":"￥1000","week":"7天","created":"2018.06.19 17:23","open":"2018.06.26","status":"已付款","btn":["7"]}],"size":20},{"_id":"1","count":10000,"type":"ICO基金","key":"ICO","color":"#c8e9a0","list":[{"orderid":"5320180508135306","name":"ICO基金","rate":[{"key":"基金净值","val":"9966.68"},{"key":"基金份额","val":"10000份"}],"payAmount":"￥10000","week":"365天","created":"2018.05.15 08:00","open":"2019.05.15","status":"已付款","btn":["7"]}],"size":1},{"_id":"4","type":"ETF指数基金","key":"ETF","color":"#50b2ef","count":25000,"list":[{"orderid":"2920180517142930","name":"马六甲比特币一号【一期】","rate":[{"key":"年化收益率","val":"105.84"},{"key":"累计收益","val":"￥678.54"},{"key":"到期收益","val":"￥6350.40"}],"payAmount":"￥6000","week":"365天","created":"2018.05.17 14:29","open":"2019.05.17","status":"挂单中"},{"orderid":"02018040416003100031","name":"马六甲比特币一号【十二期】","rate":[{"key":"年化收益率","val":"43.75"},{"key":"累计收益","val":"￥491.44"},{"key":"到期收益","val":"￥2187.50"}],"payAmount":"￥5000","week":"365天","created":"2018.04.04 16:00","open":"2019.04.04","status":"挂单中"}],"size":2},{"_id":"5","type":"BTC指数基金","key":"BTC","color":"#aad8fb","count":25000,"list":[{"orderid":"5220180619115205","name":"BTC指数基金","rate":[{"key":"年化收益率","val":"每日15:00收盘价"},{"key":"累计收益","val":"￥NaN"},{"key":"到期收益","val":"￥NaN"}],"payAmount":"￥36536","week":"365天","created":"2018.06.19 11:52","open":"2019.06.19","status":"挂单中"},{"orderid":"4320180416184321","name":"BTC指数基金","rate":[{"key":"年化收益率","val":"每日15:00收盘价"},{"key":"累计收益","val":"￥NaN"},{"key":"到期收益","val":"￥NaN"}],"payAmount":"￥6000","week":"365天","created":"2018.04.16 18:43","open":"2019.04.16","status":"挂单中"},{"orderid":"3620180416183606","name":"BTC指数基金","rate":[{"key":"年化收益率","val":"每日15:00收盘价"},{"key":"累计收益","val":"￥NaN"},{"key":"到期收益","val":"￥NaN"}],"payAmount":"￥6000","week":"365天","created":"2018.04.16 18:36","open":"2019.04.16","status":"挂单中"}],"size":3}],"all":157446}
     */

    private int status;
    private BodyBean body;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public static class BodyBean {
        /**
         * orders : [{"_id":"0","count":67188,"type":"OTC套利基金","key":"OTC","color":"#e7d878","list":[{"orderid":"2520180621152526","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥1.18"},{"key":"到期收益","val":"￥2.07"}],"payAmount":"￥2000","week":"7天","created":"2018.06.21 15:25","open":"2018.06.28","status":"已付款","btn":["7"]},{"orderid":"1320180620151314","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥1.48"},{"key":"到期收益","val":"￥2.07"}],"payAmount":"￥2000","week":"7天","created":"2018.06.20 15:13","open":"2018.06.27","status":"已付款","btn":["7"]},{"orderid":"5320180620105306","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥0.82"},{"key":"到期收益","val":"￥1.15"}],"payAmount":"￥1111","week":"7天","created":"2018.06.20 10:53","open":"2018.06.27","status":"已付款","btn":["7"]},{"orderid":"4420180619174425","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥22.42"},{"key":"到期收益","val":"￥26.15"}],"payAmount":"￥25255","week":"7天","created":"2018.06.19 17:44","open":"2018.06.26","status":"已付款","btn":["7"]},{"orderid":"2320180619172317","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥0.89"},{"key":"到期收益","val":"￥1.04"}],"payAmount":"￥1000","week":"7天","created":"2018.06.19 17:23","open":"2018.06.26","status":"已付款","btn":["7"]}],"size":20},{"_id":"3","count":2000,"type":"智能投顾","key":"ICO","color":"#f4a277","list":[{"orderid":"2520180621152526","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥1.18"},{"key":"到期收益","val":"￥2.07"}],"payAmount":"￥2000","week":"7天","created":"2018.06.21 15:25","open":"2018.06.28","status":"已付款","btn":["7"]},{"orderid":"1320180620151314","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥1.48"},{"key":"到期收益","val":"￥2.07"}],"payAmount":"￥2000","week":"7天","created":"2018.06.20 15:13","open":"2018.06.27","status":"已付款","btn":["7"]},{"orderid":"5320180620105306","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥0.82"},{"key":"到期收益","val":"￥1.15"}],"payAmount":"￥1111","week":"7天","created":"2018.06.20 10:53","open":"2018.06.27","status":"已付款","btn":["7"]},{"orderid":"4420180619174425","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥22.42"},{"key":"到期收益","val":"￥26.15"}],"payAmount":"￥25255","week":"7天","created":"2018.06.19 17:44","open":"2018.06.26","status":"已付款","btn":["7"]},{"orderid":"2320180619172317","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥0.89"},{"key":"到期收益","val":"￥1.04"}],"payAmount":"￥1000","week":"7天","created":"2018.06.19 17:23","open":"2018.06.26","status":"已付款","btn":["7"]}],"size":20},{"_id":"1","count":10000,"type":"ICO基金","key":"ICO","color":"#c8e9a0","list":[{"orderid":"5320180508135306","name":"ICO基金","rate":[{"key":"基金净值","val":"9966.68"},{"key":"基金份额","val":"10000份"}],"payAmount":"￥10000","week":"365天","created":"2018.05.15 08:00","open":"2019.05.15","status":"已付款","btn":["7"]}],"size":1},{"_id":"4","type":"ETF指数基金","key":"ETF","color":"#50b2ef","count":25000,"list":[{"orderid":"2920180517142930","name":"马六甲比特币一号【一期】","rate":[{"key":"年化收益率","val":"105.84"},{"key":"累计收益","val":"￥678.54"},{"key":"到期收益","val":"￥6350.40"}],"payAmount":"￥6000","week":"365天","created":"2018.05.17 14:29","open":"2019.05.17","status":"挂单中"},{"orderid":"02018040416003100031","name":"马六甲比特币一号【十二期】","rate":[{"key":"年化收益率","val":"43.75"},{"key":"累计收益","val":"￥491.44"},{"key":"到期收益","val":"￥2187.50"}],"payAmount":"￥5000","week":"365天","created":"2018.04.04 16:00","open":"2019.04.04","status":"挂单中"}],"size":2},{"_id":"5","type":"BTC指数基金","key":"BTC","color":"#aad8fb","count":25000,"list":[{"orderid":"5220180619115205","name":"BTC指数基金","rate":[{"key":"年化收益率","val":"每日15:00收盘价"},{"key":"累计收益","val":"￥NaN"},{"key":"到期收益","val":"￥NaN"}],"payAmount":"￥36536","week":"365天","created":"2018.06.19 11:52","open":"2019.06.19","status":"挂单中"},{"orderid":"4320180416184321","name":"BTC指数基金","rate":[{"key":"年化收益率","val":"每日15:00收盘价"},{"key":"累计收益","val":"￥NaN"},{"key":"到期收益","val":"￥NaN"}],"payAmount":"￥6000","week":"365天","created":"2018.04.16 18:43","open":"2019.04.16","status":"挂单中"},{"orderid":"3620180416183606","name":"BTC指数基金","rate":[{"key":"年化收益率","val":"每日15:00收盘价"},{"key":"累计收益","val":"￥NaN"},{"key":"到期收益","val":"￥NaN"}],"payAmount":"￥6000","week":"365天","created":"2018.04.16 18:36","open":"2019.04.16","status":"挂单中"}],"size":3}]
         * all : 157446
         */

        private Double all;
        private List<OrdersBean> orders;

        public Double getAll() {
            return all;
        }

        public void setAll(Double all) {
            this.all = all;
        }

        public List<OrdersBean> getOrders() {
            return orders;
        }

        public void setOrders(List<OrdersBean> orders) {
            this.orders = orders;
        }

        public static class OrdersBean {
            /**
             * _id : 0
             * count : 67188
             * type : OTC套利基金
             * key : OTC
             * color : #e7d878
             * list : [{"orderid":"2520180621152526","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥1.18"},{"key":"到期收益","val":"￥2.07"}],"payAmount":"￥2000","week":"7天","created":"2018.06.21 15:25","open":"2018.06.28","status":"已付款","btn":["7"]},{"orderid":"1320180620151314","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥1.48"},{"key":"到期收益","val":"￥2.07"}],"payAmount":"￥2000","week":"7天","created":"2018.06.20 15:13","open":"2018.06.27","status":"已付款","btn":["7"]},{"orderid":"5320180620105306","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥0.82"},{"key":"到期收益","val":"￥1.15"}],"payAmount":"￥1111","week":"7天","created":"2018.06.20 10:53","open":"2018.06.27","status":"已付款","btn":["7"]},{"orderid":"4420180619174425","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥22.42"},{"key":"到期收益","val":"￥26.15"}],"payAmount":"￥25255","week":"7天","created":"2018.06.19 17:44","open":"2018.06.26","status":"已付款","btn":["7"]},{"orderid":"2320180619172317","name":"OTC七日标","rate":[{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥0.89"},{"key":"到期收益","val":"￥1.04"}],"payAmount":"￥1000","week":"7天","created":"2018.06.19 17:23","open":"2018.06.26","status":"已付款","btn":["7"]}]
             * size : 20
             */

            private String _id;
            private Double count;
            private String type;
            private String key;
            private String color;
            private int size;
            private List<ListBean> list;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public Double getCount() {
                return count;
            }

            public void setCount(Double count) {
                this.count = count;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * orderid : 2520180621152526
                 * name : OTC七日标
                 * rate : [{"key":"年化收益率","val":"5.4"},{"key":"累计收益","val":"￥1.18"},{"key":"到期收益","val":"￥2.07"}]
                 * payAmount : ￥2000
                 * week : 7天$￥
                 * created : 2018.06.21 15:25
                 * open : 2018.06.28
                 * status : 已付款
                 * btn : ["7"]
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
                     * val : 5.4
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
}
