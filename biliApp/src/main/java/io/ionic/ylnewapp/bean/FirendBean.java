package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/6/19/0019.
 */

public class FirendBean {

    /**
     * status : 200
     * body : {"list":[{"_id":"5ac607a598b290170b08fd57","inviteid":"17858968657","userid":"17621752521","__v":0,"created":"2018-04-05T11:25:25.880Z","updated":"2018-04-05T11:25:25.880Z","status":"0","money":0,"payAmount":0,"tradeno":"","orderid":"","type":"0","zhifuPay":""},{"_id":"5afe9af1c935d83823ce7df6","userid":"15755528960","inviteid":"17858968657","__v":0,"created":"2018-05-18T09:20:49.482Z","updated":"2018-05-18T09:20:49.482Z","status":"0","money":0,"payAmount":0,"tradeno":"","orderid":"","type":"0","zhifuPay":""}],"person":2,"moneys":0}
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
         * list : [{"_id":"5ac607a598b290170b08fd57","inviteid":"17858968657","userid":"17621752521","__v":0,"created":"2018-04-05T11:25:25.880Z","updated":"2018-04-05T11:25:25.880Z","status":"0","money":0,"payAmount":0,"tradeno":"","orderid":"","type":"0","zhifuPay":""},{"_id":"5afe9af1c935d83823ce7df6","userid":"15755528960","inviteid":"17858968657","__v":0,"created":"2018-05-18T09:20:49.482Z","updated":"2018-05-18T09:20:49.482Z","status":"0","money":0,"payAmount":0,"tradeno":"","orderid":"","type":"0","zhifuPay":""}]
         * person : 2
         * moneys : 0
         */

        private int person;
        private int moneys;
        private List<ListBean> list;

        public int getPerson() {
            return person;
        }

        public void setPerson(int person) {
            this.person = person;
        }

        public int getMoneys() {
            return moneys;
        }

        public void setMoneys(int moneys) {
            this.moneys = moneys;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * _id : 5ac607a598b290170b08fd57
             * inviteid : 17858968657
             * userid : 17621752521
             * __v : 0
             * created : 2018-04-05T11:25:25.880Z
             * updated : 2018-04-05T11:25:25.880Z
             * status : 0
             * money : 0
             * payAmount : 0
             * tradeno :
             * orderid :
             * type : 0
             * zhifuPay :
             */

            private String _id;
            private String inviteid;
            private String userid;
            private int __v;
            private String created;
            private String updated;
            private String status;
            private int money;
            private int payAmount;
            private String tradeno;
            private String orderid;
            private String type;
            private String zhifuPay;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getInviteid() {
                return inviteid;
            }

            public void setInviteid(String inviteid) {
                this.inviteid = inviteid;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public int get__v() {
                return __v;
            }

            public void set__v(int __v) {
                this.__v = __v;
            }

            public String getCreated() {
                return created;
            }

            public void setCreated(String created) {
                this.created = created;
            }

            public String getUpdated() {
                return updated;
            }

            public void setUpdated(String updated) {
                this.updated = updated;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public int getPayAmount() {
                return payAmount;
            }

            public void setPayAmount(int payAmount) {
                this.payAmount = payAmount;
            }

            public String getTradeno() {
                return tradeno;
            }

            public void setTradeno(String tradeno) {
                this.tradeno = tradeno;
            }

            public String getOrderid() {
                return orderid;
            }

            public void setOrderid(String orderid) {
                this.orderid = orderid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getZhifuPay() {
                return zhifuPay;
            }

            public void setZhifuPay(String zhifuPay) {
                this.zhifuPay = zhifuPay;
            }
        }
    }
}
