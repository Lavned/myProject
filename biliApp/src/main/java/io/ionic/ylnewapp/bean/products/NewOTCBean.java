package io.ionic.ylnewapp.bean.products;

import java.util.List;

/**
 * Created by mogojing on 2018/9/4/0004.
 */

public class NewOTCBean {

    private List<OTCBean> OTC;

    public List<OTCBean> getOTC() {
        return OTC;
    }

    public void setOTC(List<OTCBean> OTC) {
        this.OTC = OTC;
    }

    public static class OTCBean {
        /**
         * _id : 5abafb4e85cf7d5b7ece6fa1
         * btn : 立即投资
         * key : OTC
         * name : OTC七日标
         * pid : 20160000
         * title : 全新的投资方式 币币对冲套利
         * titleRate : 年化收益率
         * titleWeek : 封闭期限
         * week : 7天
         * content : ["固定收益","1000起投"]
         * rate : 5.4%
         * state : false
         * shouyi : 5.4
         */

        private String _id;
        private String btn;
        private String key;
        private String name;
        private String pid;
        private String title;
        private String titleRate;
        private String titleWeek;
        private String week;
        private String rate;
        private boolean state;
        private String shouyi;
        private List<String> content;
        private String join;

        public String getJoin() {
            return join;
        }

        public void setJoin(String join) {
            this.join = join;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getBtn() {
            return btn;
        }

        public void setBtn(String btn) {
            this.btn = btn;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleRate() {
            return titleRate;
        }

        public void setTitleRate(String titleRate) {
            this.titleRate = titleRate;
        }

        public String getTitleWeek() {
            return titleWeek;
        }

        public void setTitleWeek(String titleWeek) {
            this.titleWeek = titleWeek;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public String getShouyi() {
            return shouyi;
        }

        public void setShouyi(String shouyi) {
            this.shouyi = shouyi;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }
    }
}
