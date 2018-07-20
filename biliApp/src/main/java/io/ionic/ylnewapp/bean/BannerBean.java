package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/7/17/0017.
 */

public class BannerBean {

    /**
     * status : 200
     * msg : [{"_id":"5b4c564f5424ac101db706c7","subTitle":"","title":"","href":"https://www.bit000.com/#/activity/bncn","alt":"马六甲比特基金","imgUrl":"https://bncn.oss-cn-beijing.aliyuncs.com/images/mb/banner%403x.png","order":"1","type":"mbili"},{"_id":"5b4c565d5424ac101db706d7","subTitle":"","title":"","href":"https://www.bit000.com/#/activity/bncn","alt":"马六甲比特基金","imgUrl":"https://bncn.oss-cn-beijing.aliyuncs.com/images/mb/banner%403x.png","order":"1","type":"mbili"}]
     */

    private int status;
    private List<MsgBean> msg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public static class MsgBean {
        /**
         * _id : 5b4c564f5424ac101db706c7
         * subTitle :
         * title :
         * href : https://www.bit000.com/#/activity/bncn
         * alt : 马六甲比特基金
         * imgUrl : https://bncn.oss-cn-beijing.aliyuncs.com/images/mb/banner%403x.png
         * order : 1
         * type : mbili
         */

        private String _id;
        private String subTitle;
        private String title;
        private String href;
        private String alt;
        private String imgUrl;
        private String order;
        private String type;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public String getAlt() {
            return alt;
        }

        public void setAlt(String alt) {
            this.alt = alt;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
