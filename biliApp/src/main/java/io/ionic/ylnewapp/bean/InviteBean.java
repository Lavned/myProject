package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/9/4/0004.
 */

public class InviteBean {


    /**
     * status : 200
     * body : {"lists":[{"_id":"5b8e3173fbc85091d4099937","userid":"17621752524","inviteid":"17858968657","stage":4,"__v":0,"created":"2018-09-04T07:17:07.875Z","updated":"2018-09-04T07:17:07.875Z","status":"2","money":0,"payAmount":0,"tradeno":"","orderid":"","type":"0","zhifuPay":""}],"packet":1676,"people":3,"moneys":25}
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
         * lists : [{"_id":"5b8e3173fbc85091d4099937","userid":"17621752524","inviteid":"17858968657","stage":4,"__v":0,"created":"2018-09-04T07:17:07.875Z","updated":"2018-09-04T07:17:07.875Z","status":"2","money":0,"payAmount":0,"tradeno":"","orderid":"","type":"0","zhifuPay":""}]
         * packet : 1676
         * people : 3
         * moneys : 25
         */

        private int packet;
        private int people;
        private int moneys;
        private List<ListsBean> lists;

        public int getPacket() {
            return packet;
        }

        public void setPacket(int packet) {
            this.packet = packet;
        }

        public int getPeople() {
            return people;
        }

        public void setPeople(int people) {
            this.people = people;
        }

        public int getMoneys() {
            return moneys;
        }

        public void setMoneys(int moneys) {
            this.moneys = moneys;
        }

        public List<ListsBean> getLists() {
            return lists;
        }

        public void setLists(List<ListsBean> lists) {
            this.lists = lists;
        }

        public static class ListsBean {
            /**
             * _id : 5b8e3173fbc85091d4099937
             * userid : 17621752524
             * inviteid : 17858968657
             * stage : 4
             * __v : 0
             * created : 2018-09-04T07:17:07.875Z
             * updated : 2018-09-04T07:17:07.875Z
             * status : 2
             * money : 0
             * payAmount : 0
             * tradeno :
             * orderid :
             * type : 0
             * zhifuPay :
             */

            private String _id;
            private String userid;
            private String inviteid;
            private int stage;
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

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getInviteid() {
                return inviteid;
            }

            public void setInviteid(String inviteid) {
                this.inviteid = inviteid;
            }

            public int getStage() {
                return stage;
            }

            public void setStage(int stage) {
                this.stage = stage;
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
