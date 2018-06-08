package io.ionic.ylnewapp.bean.response;

import java.util.List;

/**
 * Created by mogojing on 2018/6/4/0004.
 */

public class OrderBean {


    /**
     * status : 200
     * body : [{"_id":"5b028ab41e026e30ee956a88","pid":{"join":"1000","rate":"16.0","pid":"20170001","name":"比特币套利基金一季","week":"90","_id":"5a9e34c2777d663e7f9e182e"},"userid":"5ac47f83608b1d1980905c8e","username":"17858968657","orderid":"020180521170036","tradeno":"020180521170036","outtrade":"020180521170036","__v":0,"status":"1","payed":"2018-05-21T09:01:39.016Z","updated":"2018-05-21T09:00:36.742Z","created":"2018-05-21T09:00:36.742Z","openStatus":"0","openAmount":2079,"opendate":"2018-08-19T09:01:39.022Z","payAmount":2000},{"_id":"5b0278844214132703a0460c","pid":{"join":"1000","rate":"16.0","pid":"20170001","name":"比特币套利基金一季","week":"90","_id":"5a9e34c2777d663e7f9e182e"},"key":"etf","userid":"5ac47f83608b1d1980905c8e","username":"17858968657","orderid":"4320180521154300","tradeno":"4320180521154300","outtrade":"4320180521154300","__v":0,"status":"1","payed":"2018-05-21T08:22:39.670Z","updated":"2018-05-21T07:43:00.463Z","created":"2018-05-21T07:43:00.463Z","openStatus":"0","openAmount":2079,"opendate":"2018-08-19T08:22:39.685Z","payAmount":2000},{"_id":"5b027474083092252198d633","pid":{"join":"1","rate":"4.8","pid":"e20170001","name":"数字资产一季","week":"90","_id":"5a9e34c2777d663e7f9e1831"},"key":"etf","userid":"5ac47f83608b1d1980905c8e","username":"17858968657","orderid":"2520180521152540","tradeno":"2520180521152540","outtrade":"2520180521152540","__v":0,"status":"1","payed":"2018-05-21T07:26:42.188Z","updated":"2018-05-21T07:25:40.634Z","created":"2018-05-21T07:25:40.634Z","openStatus":"2","openAmount":0,"opendate":"2018-05-21T07:25:40.634Z","payAmount":2},{"_id":"5b02340e739b4905b938bad5","pid":{"join":"1000","rate":"6.4","pid":"20170000","name":"比特币套利基金新月","week":"30","_id":"5a9e34c2777d663e7f9e182d"},"key":"","userid":"5ac47f83608b1d1980905c8e","username":"17858968657","orderid":"5020180521105054","tradeno":"5020180521105054","outtrade":"5020180521105054","__v":0,"status":"1","payed":"2018-05-21T03:17:10.762Z","updated":"2018-05-21T02:50:54.987Z","created":"2018-05-21T02:50:54.987Z","openStatus":"0","openAmount":0,"opendate":"2018-05-21T02:50:54.987Z","payAmount":6000},{"_id":"5af16a6f0942f27435b12268","outtrade":"66688820180508171423","tradeno":"201805081714231423","username":"17858968657","pid":{"shouyi":"5.4","date":"2018-05-08T09:14:23.496Z","state":false,"join":"1000","rate":"5.4","pid":"20160000","name":"OTC七日标","week":"7","key":"OTC","_id":"5abafb4e85cf7d5b7ece6fa1"},"orderid":"1420180508171423","userid":"5ac47f83608b1d1980905c8e","__v":0,"status":"1","payed":"2018-05-08T09:14:23.496Z","updated":"2018-05-08T09:14:23.496Z","created":"2018-05-08T09:14:23.496Z","openStatus":"0","openAmount":5005,"opendate":"2018-05-08T09:14:23.497Z","payAmount":5000},{"_id":"5afd214a35a6fc266780e063","pid":{"join":"5000","rate":"105.84","pid":"s20170000","name":"马六甲比特币一号【一期】","week":"365","_id":"5a9e34c2777d663e7f9e1836"},"payAmount":6000,"userid":"5ac47f83608b1d1980905c8e","username":"17858968657","orderid":"2920180517142930","outtrade":"2920180517142930","__v":0,"status":"1","isContinue":false,"continued":"2018-05-17T06:29:30.621Z","updated":"2018-05-17T06:29:30.621Z","created":"2018-05-17T06:29:30.621Z","started":"2017-05-13T00:00:00.000Z","type":"1","openStatus":"0","openAmount":0,"putstatus":"0"},{"_id":"5af1429bd21fb755bc3263da","outtrade":"66688820180508142427","tradeno":"201805081424272427","username":"17858968657","pid":{"shouyi":"5.4","date":"2018-05-08T06:24:27.848Z","state":false,"join":"1000","rate":"5.4","pid":"20160000","name":"OTC七日标","week":"7","key":"OTC","_id":"5abafb4e85cf7d5b7ece6fa1"},"orderid":"2420180508142427","userid":"5ac47f83608b1d1980905c8e","__v":0,"status":"1","payed":"2018-05-08T06:24:27.848Z","updated":"2018-05-08T06:24:27.848Z","created":"2018-05-08T06:24:27.848Z","openStatus":"0","openAmount":1001,"opendate":"2018-05-08T06:24:27.849Z","payAmount":1000},{"_id":"5ad47e49558fa967474650a4","payAmount":6000,"username":"17858968657","pid":{"pid":"b20170000","name":"BTC指数基金","shouyi":"浮动收益","week":365,"join":5000},"orderid":"4320180416184321","userid":"5ac47f83608b1d1980905c8e","__v":0,"payed":"2018-04-16T10:43:21.087Z","status":"1","updated":"2018-04-16T10:43:21.087Z","created":"2018-04-16T10:43:21.087Z","type":"1","openStatus":"0","openAmount":0,"putstatus":"0","flag":"0","fen":0},{"_id":"5ac4861f608b1d1980905c92","payAmount":5000,"username":"17858968657","pid":{"type":11,"shouyi":"0","date":"2018-04-01T00:00:00.000Z","state":true,"join":"5000","rate":"0","__v":0,"pid":"s20170011","name":"马六甲比特币一号【十二期】","week":"365","key":"ETF","_id":"5aa1ec493dcecd52ccd27250"},"orderid":"02018040416003100031","userid":"5ac47f83608b1d1980905c8e","__v":0,"payed":"2018-04-04T08:00:31.233Z","status":"1","isContinue":false,"continued":"2018-06-04T02:43:29.007Z","updated":"2018-04-04T08:00:31.233Z","created":"2018-04-04T08:00:31.233Z","started":"2018-04-01T00:00:00.000Z","type":"11","openStatus":"0","openAmount":0,"putstatus":"0"}]
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
         * _id : 5b028ab41e026e30ee956a88
         * pid : {"join":"1000","rate":"16.0","pid":"20170001","name":"比特币套利基金一季","week":"90","_id":"5a9e34c2777d663e7f9e182e"}
         * userid : 5ac47f83608b1d1980905c8e
         * username : 17858968657
         * orderid : 020180521170036
         * tradeno : 020180521170036
         * outtrade : 020180521170036
         * __v : 0
         * status : 1
         * payed : 2018-05-21T09:01:39.016Z
         * updated : 2018-05-21T09:00:36.742Z
         * created : 2018-05-21T09:00:36.742Z
         * openStatus : 0
         * openAmount : 2079
         * opendate : 2018-08-19T09:01:39.022Z
         * payAmount : 2000
         * key : etf
         * isContinue : false
         * continued : 2018-05-17T06:29:30.621Z
         * started : 2017-05-13T00:00:00.000Z
         * type : 1
         * putstatus : 0
         * flag : 0
         * fen : 0
         */

        private String _id;
        private PidBean pid;
        private String userid;
        private String username;
        private String orderid;
        private String tradeno;
        private String outtrade;
        private int __v;
        private String status;
        private String payed;
        private String updated;
        private String created;
        private String openStatus;
        private int openAmount;
        private String opendate;
        private int payAmount;
        private String key;
        private boolean isContinue;
        private String continued;
        private String started;
        private String type;
        private String putstatus;
        private String flag;
        private int fen;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public PidBean getPid() {
            return pid;
        }

        public void setPid(PidBean pid) {
            this.pid = pid;
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

        public String getOrderid() {
            return orderid;
        }

        public void setOrderid(String orderid) {
            this.orderid = orderid;
        }

        public String getTradeno() {
            return tradeno;
        }

        public void setTradeno(String tradeno) {
            this.tradeno = tradeno;
        }

        public String getOuttrade() {
            return outtrade;
        }

        public void setOuttrade(String outtrade) {
            this.outtrade = outtrade;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayed() {
            return payed;
        }

        public void setPayed(String payed) {
            this.payed = payed;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getOpenStatus() {
            return openStatus;
        }

        public void setOpenStatus(String openStatus) {
            this.openStatus = openStatus;
        }

        public int getOpenAmount() {
            return openAmount;
        }

        public void setOpenAmount(int openAmount) {
            this.openAmount = openAmount;
        }

        public String getOpendate() {
            return opendate;
        }

        public void setOpendate(String opendate) {
            this.opendate = opendate;
        }

        public int getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public boolean isIsContinue() {
            return isContinue;
        }

        public void setIsContinue(boolean isContinue) {
            this.isContinue = isContinue;
        }

        public String getContinued() {
            return continued;
        }

        public void setContinued(String continued) {
            this.continued = continued;
        }

        public String getStarted() {
            return started;
        }

        public void setStarted(String started) {
            this.started = started;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getPutstatus() {
            return putstatus;
        }

        public void setPutstatus(String putstatus) {
            this.putstatus = putstatus;
        }

        public String getFlag() {
            return flag;
        }

        public void setFlag(String flag) {
            this.flag = flag;
        }

        public int getFen() {
            return fen;
        }

        public void setFen(int fen) {
            this.fen = fen;
        }

        public static class PidBean {
            /**
             * join : 1000
             * rate : 16.0
             * pid : 20170001
             * name : 比特币套利基金一季
             * week : 90
             * _id : 5a9e34c2777d663e7f9e182e
             */

            private String join;
            private String rate;
            private String pid;
            private String name;
            private String week;
            private String _id;

            public String getJoin() {
                return join;
            }

            public void setJoin(String join) {
                this.join = join;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }
        }
    }
}
