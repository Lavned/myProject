package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/6/6/0006.
 */

public class ActivityBean {


    /**
     * status : 200
     * body : [{"url":["https://bncn.oss-cn-beijing.aliyuncs.com/bili/bncn%402x.png","https://bncn.oss-cn-beijing.aliyuncs.com/bili/bncn%403x.png"],"title":"领取优惠券","id":"packet","date":"2018-6-1"},{"url":["https://bncn.oss-cn-beijing.aliyuncs.com/bili/packet%402x.png","https://bncn.oss-cn-beijing.aliyuncs.com/bili/packet%403x.png"],"title":"领取BNCN","id":"bncn","date":"2018-6-1"}]
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
         * url : ["https://bncn.oss-cn-beijing.aliyuncs.com/bili/bncn%402x.png",
         * "https://bncn.oss-cn-beijing.aliyuncs.com/bili/bncn%403x.png"]
         * title : 领取优惠券
         * id : packet
         * date : 2018-6-1
         * httprul :""
         */

        private String title;
        private String id;
        private String date;
        private List<String> url;
        private String href;

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<String> getUrl() {
            return url;
        }

        public void setUrl(List<String> url) {
            this.url = url;
        }
    }
}
