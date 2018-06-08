package io.ionic.ylnewapp.bean.response;

import java.util.List;

/**
 * Created by mogojing on 2018/6/6/0006.
 */

public class CoinBean {

    /**
     * status : 200
     * body : [{"key":"BTC","name":"比特币","id":"3","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"ETH","name":"以太坊","id":"2","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"BNCN","name":"","id":"1","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":true},{"key":"XRP","name":"瑞波币","id":"5","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"BCH","name":"比特现金","id":"6","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"EOS","name":"柚子","id":"7","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"LTC","name":"莱特币","id":"8","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"ADA","name":"艾达币","id":"9","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"XLM","name":"恒星币","id":"10","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"MIOTA","name":"埃欧塔","id":"11","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"TRX","name":"波场","id":"12","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"NEO","name":"小蚁","id":"13","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"XMR","name":"门罗币","id":"14","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"DASH","name":"达世币","id":"15","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"USDT","name":"泰达币","id":"16","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"XEM","name":"新经币","id":"17","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"VEN","name":"唯链","id":"18","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"BNB","name":"币安币","id":"19","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"ETC","name":"以太经典","id":"20","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"OKB","name":"OK币","id":"21","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"HT","name":"火币积分","id":"22","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"QTUM","name":"量子链","id":"23","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"OMG","name":"嫩模币","id":"24","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"BCN","name":"字节币","id":"25","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"ICX","name":"","id":"26","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"LSK","name":"应用链","id":"27","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"ZEC","name":"大零币","id":"28","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"AE","name":"阿姨币","id":"29","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"BTG","name":"比特黄金","id":"30","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"DCR","name":"","id":"31","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"ZRX","name":"0x协议","id":"32","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"BTM","name":"比原链","id":"33","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"STEEM","name":"斯蒂姆币","id":"34","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"BTS","name":"比特股","id":"35","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"XVG","name":"","id":"36","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"SC","name":"云储币","id":"37","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"NANO","name":"纳诺","id":"38","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"GNT","name":"","id":"39","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"MKR","name":"","id":"40","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"PPT","name":"","id":"41","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"STRAT","name":"","id":"42","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"REP","name":"","id":"43","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"WAVES","name":"波币","id":"44","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"DOGE","name":"狗狗币","id":"45","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"XUC","name":"雪币","id":"46","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"IOST","name":"","id":"47","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"DGB","name":"极特币","id":"48","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"WTC","name":"沃尔顿链","id":"49","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"SNT","name":"","id":"50","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"DGD","name":"黄金代币","id":"51","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"AION","name":"","id":"52","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"HSR","name":"红烧肉","id":"53","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"LRC","name":"露印协议","id":"54","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"BAT","name":"注意力币","id":"55","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"ELF","name":"","id":"56","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"KMD","name":"科莫多币","id":"57","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"WICC","name":"维基链","id":"58","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"ARK","name":"","id":"59","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"CMT","name":"草莓糖","id":"60","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"ARDR","name":"阿朵币","id":"61","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false},{"key":"MAID","name":"互联网币","id":"62","icon":"http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png","statu":false}]
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
         * key : BTC
         * name : 比特币
         * id : 3
         * icon : http://static.feixiaohao.com/coin/7033f2f2c2a16094bbb3bafc47205ba8_small.png
         * statu : false
         */

        private String key;
        private String name;
        private String id;
        private String icon;
        private boolean statu;

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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public boolean isStatu() {
            return statu;
        }

        public void setStatu(boolean statu) {
            this.statu = statu;
        }
    }
}
