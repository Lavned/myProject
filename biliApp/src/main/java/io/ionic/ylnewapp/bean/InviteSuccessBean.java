package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/9/4/0004.
 */

public class InviteSuccessBean {

    /**
     * status : 200
     * body : {"lists":[{"_id":"5b8e3505fdb0c5a91b94e724","userid":"5ac47f83608b1d1980905c8e","username":"17621752524","inviteid":"17858968657","orderid":"3220180904153213m4","type":"4","countn":5,"payAmount":5,"rate":"1%","kind":"复投用户","stage":4,"__v":0,"created":"2018-09-04T07:32:21.963Z","updated":"2018-09-04T07:32:21.963Z","status":"1","tradeno":"","content":"邀请好友奖励5元"},{"_id":"5b8e341e3f90fda11cf05a54","userid":"5ac47f83608b1d1980905c8e","username":"17621752521","inviteid":"17858968657","orderid":"2720180904152721m4","type":"4","countn":10,"payAmount":10,"rate":"1%","kind":"新用户","stage":4,"__v":0,"created":"2018-09-04T07:28:30.881Z","updated":"2018-09-04T07:28:30.881Z","status":"1","tradeno":"","content":"邀请好友奖励10元"},{"_id":"5b8e22818917082b83b67770","userid":"5ac47f83608b1d1980905c8e","username":"17621752521","inviteid":"17858968657","orderid":"1920180903171936m3","type":"4","countn":10,"payAmount":10,"rate":"2%","kind":"复投用户","stage":3,"__v":0,"created":"2018-09-04T06:13:21.009Z","updated":"2018-09-04T06:13:21.009Z","status":"1","tradeno":"","content":"邀请好友奖励10元"}],"people":3,"moneys":25}
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
         * lists : [{"_id":"5b8e3505fdb0c5a91b94e724","userid":"5ac47f83608b1d1980905c8e","username":"17621752524","inviteid":"17858968657","orderid":"3220180904153213m4","type":"4","countn":5,"payAmount":5,"rate":"1%","kind":"复投用户","stage":4,"__v":0,"created":"2018-09-04T07:32:21.963Z","updated":"2018-09-04T07:32:21.963Z","status":"1","tradeno":"","content":"邀请好友奖励5元"},{"_id":"5b8e341e3f90fda11cf05a54","userid":"5ac47f83608b1d1980905c8e","username":"17621752521","inviteid":"17858968657","orderid":"2720180904152721m4","type":"4","countn":10,"payAmount":10,"rate":"1%","kind":"新用户","stage":4,"__v":0,"created":"2018-09-04T07:28:30.881Z","updated":"2018-09-04T07:28:30.881Z","status":"1","tradeno":"","content":"邀请好友奖励10元"},{"_id":"5b8e22818917082b83b67770","userid":"5ac47f83608b1d1980905c8e","username":"17621752521","inviteid":"17858968657","orderid":"1920180903171936m3","type":"4","countn":10,"payAmount":10,"rate":"2%","kind":"复投用户","stage":3,"__v":0,"created":"2018-09-04T06:13:21.009Z","updated":"2018-09-04T06:13:21.009Z","status":"1","tradeno":"","content":"邀请好友奖励10元"}]
         * people : 3
         * moneys : 25
         */

        private int people;
        private int moneys;
        private List<ListsBean> lists;

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
             * _id : 5b8e3505fdb0c5a91b94e724
             * userid : 5ac47f83608b1d1980905c8e
             * username : 17621752524
             * inviteid : 17858968657   手机号
             * orderid : 3220180904153213m4
             * type : 4
             * countn : 5
             * payAmount : 5   金额
             * rate : 1%   返点
             * kind : 复投用户    类别
             * stage : 4  级别
             * __v : 0
             * created : 2018-09-04T07:32:21.963Z   时间
             * updated : 2018-09-04T07:32:21.963Z
             * status : 1
             * tradeno :
             * content : 邀请好友奖励5元
             */

            private String _id;
            private String userid;
            private String username;
            private String inviteid;
            private String orderid;
            private String type;
            private int countn;
            private int payAmount;
            private String rate;
            private String kind;
            private int stage;
            private int __v;
            private String created;
            private String updated;
            private String status;
            private String tradeno;
            private String content;

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

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getInviteid() {
                return inviteid;
            }

            public void setInviteid(String inviteid) {
                this.inviteid = inviteid;
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

            public int getCountn() {
                return countn;
            }

            public void setCountn(int countn) {
                this.countn = countn;
            }

            public int getPayAmount() {
                return payAmount;
            }

            public void setPayAmount(int payAmount) {
                this.payAmount = payAmount;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getKind() {
                return kind;
            }

            public void setKind(String kind) {
                this.kind = kind;
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

            public String getTradeno() {
                return tradeno;
            }

            public void setTradeno(String tradeno) {
                this.tradeno = tradeno;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }
    }
}
