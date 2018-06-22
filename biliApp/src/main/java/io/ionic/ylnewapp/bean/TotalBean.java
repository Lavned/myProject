package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/6/15/0015.
 */

public class TotalBean {


    /**
     * status : 200
     * body : {"orders":[{"_id":"2","type":"法币余额","color":"#46c099","count":98445},{"_id":"6","type":"数字货币余额","color":"#8180ff","count":0},{"_id":"3","count":2000,"type":"智能投顾","color":"#f4a277"},{"_id":"0","count":26000,"type":"OTC套利基金","color":"#e7d878"},{"_id":"4","type":"ETF指数基金","color":"#50b2ef","count":25000},{"_id":"5","type":"BTC指数基金","color":"#aad8fb","count":25000}],"all":157445}
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
         * orders : [{"_id":"2","type":"法币余额","color":"#46c099","count":98445},{"_id":"6","type":"数字货币余额","color":"#8180ff","count":0},{"_id":"3","count":2000,"type":"智能投顾","color":"#f4a277"},{"_id":"0","count":26000,"type":"OTC套利基金","color":"#e7d878"},{"_id":"4","type":"ETF指数基金","color":"#50b2ef","count":25000},{"_id":"5","type":"BTC指数基金","color":"#aad8fb","count":25000}]
         * all : 157445
         */

        private int all;
        private List<OrdersBean> orders;

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
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
             * _id : 2
             * type : 法币余额
             * color : #46c099
             * count : 98445
             */

            private String _id;
            private String type;
            private String color;
            private int count;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }
        }
    }
}
