package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/6/14/0014.
 */

public class GetMeBean {


    /**
     * status : 200
     * body : {"_id":"5a0c0e2303e4ca1896316850","password":"96e79218965eb72c92a549dd5a330112","username":"15821094419","mobStatus":"0","waddress":"","baddress":"","created":"2017-11-15T09:51:31.508Z","updated":"2018-04-08T06:46:01.650Z","address":[{"address":"0x111111111222222","type":"钱包33","id":"20180613105053"}],"banks":[{"count":"6222081001011788753","name":"中国工商银行上海金杨支行","id":"20180522142656"},{"count":"6222081001011788753","name":"中国银行上海金杨支行","id":"20180522142656"},{"count":"6222081001011788753","name":"金杨支行","id":"20180522142656"}],"bankcount":"","bankcode":"","name":"测试账号11","idcard":"340808199909096112","roletype":"0","is_admin":false,"zhifuPay":"222222","zijinPay":"","qianbao":1690,"email":"1587745011@qq.co","weixin":"g548654","qq":"1582245144","header":""}
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
         * _id : 5a0c0e2303e4ca1896316850
         * password : 96e79218965eb72c92a549dd5a330112
         * username : 15821094419
         * mobStatus : 0
         * waddress :
         * baddress :
         * created : 2017-11-15T09:51:31.508Z
         * updated : 2018-04-08T06:46:01.650Z
         * address : [{"address":"0x111111111222222","type":"钱包33","id":"20180613105053"}]
         * banks : [{"count":"6222081001011788753","name":"中国工商银行上海金杨支行","id":"20180522142656"},{"count":"6222081001011788753","name":"中国银行上海金杨支行","id":"20180522142656"},{"count":"6222081001011788753","name":"金杨支行","id":"20180522142656"}]
         * bankcount :
         * bankcode :
         * name : 测试账号11
         * idcard : 340808199909096112
         * roletype : 0
         * is_admin : false
         * zhifuPay : 222222
         * zijinPay :
         * qianbao : 1690
         * email : 1587745011@qq.co
         * weixin : g548654
         * qq : 1582245144
         * header :
         */

        private String _id;
        private String password;
        private String username;
        private String mobStatus;
        private String waddress;
        private String baddress;
        private String created;
        private String updated;
        private String bankcount;
        private String bankcode;
        private String name;
        private String idcard;
        private String roletype;
        private boolean is_admin;
        private String zhifuPay;
        private String zijinPay;
        private String qianbao;
        private String email;
        private String weixin;
        private String qq;
        private String header;
        private List<AddressBean> address;
        private List<BanksBean> banks;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getMobStatus() {
            return mobStatus;
        }

        public void setMobStatus(String mobStatus) {
            this.mobStatus = mobStatus;
        }

        public String getWaddress() {
            return waddress;
        }

        public void setWaddress(String waddress) {
            this.waddress = waddress;
        }

        public String getBaddress() {
            return baddress;
        }

        public void setBaddress(String baddress) {
            this.baddress = baddress;
        }

        public String getCreated() {
            return created;
        }

        public void setCreated(String created) {
            this.created = created;
        }

        public String getUpdated() {
            return updated;
        }

        public void setUpdated(String updated) {
            this.updated = updated;
        }

        public String getBankcount() {
            return bankcount;
        }

        public void setBankcount(String bankcount) {
            this.bankcount = bankcount;
        }

        public String getBankcode() {
            return bankcode;
        }

        public void setBankcode(String bankcode) {
            this.bankcode = bankcode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getRoletype() {
            return roletype;
        }

        public void setRoletype(String roletype) {
            this.roletype = roletype;
        }

        public boolean isIs_admin() {
            return is_admin;
        }

        public void setIs_admin(boolean is_admin) {
            this.is_admin = is_admin;
        }

        public String getZhifuPay() {
            return zhifuPay;
        }

        public void setZhifuPay(String zhifuPay) {
            this.zhifuPay = zhifuPay;
        }

        public String getZijinPay() {
            return zijinPay;
        }

        public void setZijinPay(String zijinPay) {
            this.zijinPay = zijinPay;
        }

        public String getQianbao() {
            return qianbao;
        }

        public void setQianbao(String qianbao) {
            this.qianbao = qianbao;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public List<AddressBean> getAddress() {
            return address;
        }

        public void setAddress(List<AddressBean> address) {
            this.address = address;
        }

        public List<BanksBean> getBanks() {
            return banks;
        }

        public void setBanks(List<BanksBean> banks) {
            this.banks = banks;
        }

        public static class AddressBean {
            /**
             * address : 0x111111111222222
             * type : 钱包33
             * id : 20180613105053
             */

            private String address;
            private String type;
            private String id;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class BanksBean {
            /**
             * count : 6222081001011788753
             * name : 中国工商银行上海金杨支行
             * id : 20180522142656
             */

            private String count;
            private String name;
            private String id;

            public String getCount() {
                return count;
            }

            public void setCount(String count) {
                this.count = count;
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
        }
    }
}
