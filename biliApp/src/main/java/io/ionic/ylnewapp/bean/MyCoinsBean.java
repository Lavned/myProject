package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/6/12/0012.
 */

public class MyCoinsBean {

    /**
     * status : 200
     * body : [{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"BNCN","coinid":"c23201804131723101","type":"1","cmount":0,"price":3.88},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"BTC","coinid":"c3720180607113752","type":"3","cmount":0,"price":73014.42},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"XRP","coinid":"c3820180607113840","type":"5","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"ETH","coinid":"c5220180607115236","type":"2","cmount":0,"price":5385.43},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"DASH","coinid":"c5320180607115352","type":"15","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"BNB","coinid":"c720180607140740","type":"19","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"ETC","coinid":"c1020180607141027","type":"20","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"AE","coinid":"c1020180607141035","type":"29","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"NANO","coinid":"c1020180607141040","type":"38","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"IOST","coinid":"c1020180607141050","type":"47","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"ELF","coinid":"c1220180607141228","type":"56","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"BCH","coinid":"c720180607150707","type":"6","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"EOS","coinid":"c720180607150721","type":"7","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"LTC","coinid":"c1020180607151007","type":"8","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"ADA","coinid":"c2120180607152155","type":"9","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"NEO","coinid":"c2720180607152704","type":"13","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"BTG","coinid":"c5020180607155024","type":"30","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"XLM","coinid":"c2320180607192332","type":"10","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"OMG","coinid":"c2320180607192340","type":"24","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"OKB","coinid":"c1920180608161949","type":"21","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"MIOTA","coinid":"c3120180608173105","type":"11","cmount":0,"price":0},{"icon":"http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png","name":"TRX","coinid":"c3120180608173112","type":"12","cmount":0,"price":0}]
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
         * icon : http://static.feixiaohao.com/coin/eced1e28da4f16e117f471b08ad6e_mid.png
         * name : BNCN
         * coinid : c23201804131723101
         * type : 1
         * cmount : 0
         * price : 3.88
         */

        private String icon;
        private String name;
        private String coinid;
        private String type;
        private int cmount;
        private double price;

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCoinid() {
            return coinid;
        }

        public void setCoinid(String coinid) {
            this.coinid = coinid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getCmount() {
            return cmount;
        }

        public void setCmount(int cmount) {
            this.cmount = cmount;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }
}
