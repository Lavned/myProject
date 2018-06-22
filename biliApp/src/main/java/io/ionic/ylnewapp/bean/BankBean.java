package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/5/30/0030.
 */

public class BankBean {


    /**
     * status : 200
     * body : [{"id":"20180522142656","name":"中国工商银行上海金杨支行","count":"6222081001011788753","url":["http://bncn.oss-cn-beijing.aliyuncs.com/bili/%E5%B7%A5%E5%95%86%402x.png","http://bncn.oss-cn-beijing.aliyuncs.com/bili/%E5%B7%A5%E5%95%86%403x.png"]},{"id":"20180522142656","name":"中国银行上海金杨支行","count":"6222081001011788753","url":["http://bncn.oss-cn-beijing.aliyuncs.com/bili/%E4%B8%AD%E5%9B%BD%402x.png","http://bncn.oss-cn-beijing.aliyuncs.com/bili/%E4%B8%AD%E5%9B%BD%403x.png"]}]
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
         * id : 20180522142656
         * name : 中国工商银行上海金杨支行
         * count : 6222081001011788753
         * url : ["http://bncn.oss-cn-beijing.aliyuncs.com/bili/%E5%B7%A5%E5%95%86%402x.png","http://bncn.oss-cn-beijing.aliyuncs.com/bili/%E5%B7%A5%E5%95%86%403x.png"]
         */

        private String id;
        private String name;
        private String count;
        private List<String> url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public List<String> getUrl() {
            return url;
        }

        public void setUrl(List<String> url) {
            this.url = url;
        }
    }
}
