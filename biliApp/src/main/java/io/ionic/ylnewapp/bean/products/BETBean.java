package io.ionic.ylnewapp.bean.products;

import java.util.List;

/**
 * Created by mogojing on 2018/8/24/0024.
 */

public class BETBean {

    private List<BETBean> BET;

    public List<BETBean> getBET() {
        return BET;
    }

    public void setBET(List<BETBean> BET) {
        this.BET = BET;
    }

        /**
         * _id : 5b7baeb6edb09c446d6f285f
         * btn : 立即投资
         * key : BET1
         * name : 稳赢数字基金稳赢一号半年
         * pid : d201801
         * title : 全新的投资方式 币币对冲套利
         * titleRate : 年化收益率
         * titleWeek : 封闭期限
         * week : 6月
         * content : ["固定收益","1000起投"]
         * rate : 6%+2%
         * shouyi : 2%
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
        private String shouyi;
        private List<String> content;

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
