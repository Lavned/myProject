package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/6/6/0006.
 */

public class ActivitiesDetailBean {

    /**
     * status : 200
     * body : [{"name":"588优惠券+OTC新月标/7天标","type":"1","statu":true},{"name":"988优惠券+OTC7天标","type":"2","statu":false}]
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
         * name : 588优惠券+OTC新月标/7天标
         * type : 1
         * statu : true
         */

        private String name;
        private String type;
        private boolean statu;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public boolean isStatu() {
            return statu;
        }

        public void setStatu(boolean statu) {
            this.statu = statu;
        }
    }
}
