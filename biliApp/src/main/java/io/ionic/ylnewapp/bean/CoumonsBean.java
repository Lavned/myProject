package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/5/30/0030.
 */

public class CoumonsBean {

    /**
     * status : 200
     * body : [{"_id":"5b0e4fef261a871f58520c14","userid":"5ac47f83608b1d1980905c8e","packetid":"p1720180530151703","ended":"2018-06-29T07:17:03.012Z","amount":588,"name":"OTC7天标/新月标","__v":0,"status":"1","type":"1","duration":"30","started":"2018-05-30T07:17:03.012Z"}]
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
         * _id : 5b0e4fef261a871f58520c14
         * userid : 5ac47f83608b1d1980905c8e
         * packetid : p1720180530151703
         * ended : 2018-06-29T07:17:03.012Z
         * amount : 588
         * name : OTC7天标/新月标
         * __v : 0
         * status : 1
         * type : 1
         * duration : 30
         * started : 2018-05-30T07:17:03.012Z
         */

        private String _id;
        private String userid;
        private String packetid;
        private String ended;
        private int amount;
        private String name;
        private int __v;
        private String status;
        private String type;
        private String duration;
        private String started;

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

        public String getPacketid() {
            return packetid;
        }

        public void setPacketid(String packetid) {
            this.packetid = packetid;
        }

        public String getEnded() {
            return ended;
        }

        public void setEnded(String ended) {
            this.ended = ended;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getStarted() {
            return started;
        }

        public void setStarted(String started) {
            this.started = started;
        }
    }
}
