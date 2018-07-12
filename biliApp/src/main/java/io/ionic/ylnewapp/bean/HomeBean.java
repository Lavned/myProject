package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/6/26/0026.
 */

public class HomeBean {

    /**
     * status : 200
     * body : {"push":[{"_id":"5a9e34c2777d663e7f9e1833","key":"DIG","week":"365","name":"数字资产全年","title":"法币优选项目,创造跟高价值","titleRate":"今日年化收益率","titleWeek":"期限","rate":"9.6"},{"_id":"5a9e34c2777d663e7f9e182e","key":"OTC","week":"90","name":"比特币套利基金一季","title":"全新的投资方式 币币对冲套利","titleRate":"年化收益率","titleWeek":"封闭期限","rate":"16.0"},{"_id":"5abafb4e85cf7d5b7ece6fa1","key":"OTC","week":"7","name":"OTC七日标","title":"全新的投资方式 币币对冲套利","titleRate":"年化收益率","titleWeek":"封闭期限","rate":"5.4"}],"hot":[{"_id":"5a9e34c2777d663e7f9e1834","key":"ICO","name":"ICO基金","text":["预期年化收益","私募机构尽职调查，筛选项目"],"rate":"请关注QQ群"},{"_id":"5a9e34c2777d663e7f9e1830","key":"OTC","name":"比特币套利基金全年","text":["固定年化收益","程序套利，赚取行业红利"],"rate":"32.0%"},{"_id":"5acc234a94d4626eed591d51","key":"ETF","name":"马六甲比特币一号【 十三期】","text":["浮动年化收益","60%长期持有，40%对冲套利"],"rate":"0%"}]}
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
        private List<PushBean> push;
        private List<HotBean> hot;

        public List<PushBean> getPush() {
            return push;
        }

        public void setPush(List<PushBean> push) {
            this.push = push;
        }

        public List<HotBean> getHot() {
            return hot;
        }

        public void setHot(List<HotBean> hot) {
            this.hot = hot;
        }

        public static class PushBean {
            /**
             * _id : 5a9e34c2777d663e7f9e1833
             * key : DIG
             * week : 365
             * name : 数字资产全年
             * title : 法币优选项目,创造跟高价值
             * titleRate : 今日年化收益率
             * titleWeek : 期限
             * rate : 9.6
             */

            private String _id;
            private String key;
            private String week;
            private String name;
            private String title;
            private String titleRate;
            private String titleWeek;
            private String rate;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
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

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }
        }

        public static class HotBean {
            /**
             * _id : 5a9e34c2777d663e7f9e1834
             * key : ICO
             * name : ICO基金
             * text : ["预期年化收益","私募机构尽职调查，筛选项目"]
             * rate : 请关注QQ群
             */

            private String _id;
            private String key;
            private String name;
            private String rate;
            private List<String> text;

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
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

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public List<String> getText() {
                return text;
            }

            public void setText(List<String> text) {
                this.text = text;
            }
        }
    }
}
