package io.ionic.ylnewapp.bean.products;

import java.util.List;

/**
 * Created by mogojing on 2018/8/17/0017.
 */

public class ProducetDetailBean {

    /**
     * status : 200
     * body : {"icon":"https://bncn.oss-cn-beijing.aliyuncs.com/bili/undefined%403x.png","_id":"5a9e34c2777d663e7f9e1830","__v":0,"btn":"立即投资","hot":1,"key":"OTC","name":"比特币套利基金全年","pid":"20170003","title":"全新的投资方式 币币对冲套利","titleRate":"年化收益率","titleWeek":"封闭期限","week":"365","max":1000,"text":["固定年化收益","程序套利，赚取行业红利"],"content":["固定收益","1000起投"],"rate":"24.0","join":"1000元","state":false,"date":"2018-08-17T02:55:47.877Z","shouyi":"24.0"}
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
         * icon : https://bncn.oss-cn-beijing.aliyuncs.com/bili/undefined%403x.png
         * _id : 5a9e34c2777d663e7f9e1830
         * __v : 0
         * btn : 立即投资
         * hot : 1
         * key : OTC
         * name : 比特币套利基金全年
         * pid : 20170003
         * title : 全新的投资方式 币币对冲套利
         * titleRate : 年化收益率
         * titleWeek : 封闭期限
         * week : 365
         * max : 1000
         * text : ["固定年化收益","程序套利，赚取行业红利"]
         * content : ["固定收益","1000起投"]
         * rate : 24.0
         * join : 1000元
         * state : false
         * date : 2018-08-17T02:55:47.877Z
         * shouyi : 24.0
         */

        private String icon;
        private String _id;
        private int __v;
        private String btn;
        private int hot;
        private String key;
        private String name;
        private String pid;
        private String title;
        private String titleRate;
        private String titleWeek;
        private String week;
        private int max;
        private String rate;
        private String join;
        private boolean state;
        private String date;
        private String shouyi;
        private List<String> text;
        private List<String> content;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public int get__v() {
            return __v;
        }

        public void set__v(int __v) {
            this.__v = __v;
        }

        public String getBtn() {
            return btn;
        }

        public void setBtn(String btn) {
            this.btn = btn;
        }

        public int getHot() {
            return hot;
        }

        public void setHot(int hot) {
            this.hot = hot;
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

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getJoin() {
            return join;
        }

        public void setJoin(String join) {
            this.join = join;
        }

        public boolean isState() {
            return state;
        }

        public void setState(boolean state) {
            this.state = state;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getShouyi() {
            return shouyi;
        }

        public void setShouyi(String shouyi) {
            this.shouyi = shouyi;
        }

        public List<String> getText() {
            return text;
        }

        public void setText(List<String> text) {
            this.text = text;
        }

        public List<String> getContent() {
            return content;
        }

        public void setContent(List<String> content) {
            this.content = content;
        }
    }
}
