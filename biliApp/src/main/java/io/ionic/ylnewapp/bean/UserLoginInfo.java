package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/5/11/0011.
 */

public class UserLoginInfo {


    /**
     * status : 200
     * msg : 登录成功
     * body : {"_id":"5ac47f83608b1d1980905c8e","password":"96e79218965eb72c92a549dd5a330112","username":"17858968657","mobStatus":"0","created":"2018-04-04T07:32:19.604Z","updated":"2018-04-04T07:32:19.604Z","address":[{"address":"ggg","type":"555","id":"20180613144711"},{"address":"222","type":"222","id":"20180613145021"},{"address":"33333","type":"2222","id":"20180613145404"}],"banks":[{"count":"6222081001011788753","name":"中国工商银行上海金杨支行","id":"20180522142656"},{"count":"6222081001011788753","name":"中国银行上海金杨支行","id":"20180522142656"}],"bankcount":"","bankcode":"","name":"泾甪路","idcard":"622425199308257329","roletype":"0","is_admin":false,"zhifuPay":"93279e3308bdbbeed946fc965017f67a","zijinPay":"","qianbao":68457,"email":"","weixin":"","qq":"","header":""}
     * token : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyIkX18iOnsic3RyaWN0TW9kZSI6dHJ1ZSwic2VsZWN0ZWQiOnsibW9TdGF0dXMiOjAsIm1haWxTZWMiOjAsInVzZXJTdGF0dXMiOjAsIm90aGVyVGVsIjowLCJfX3YiOjAsImxhc3RUaW1lIjowLCJub3dUaW1lIjowLCJyZW1vdGVpcCI6MH0sImdldHRlcnMiOnt9LCJfaWQiOiI1YWM0N2Y4MzYwOGIxZDE5ODA5MDVjOGUiLCJ3YXNQb3B1bGF0ZWQiOmZhbHNlLCJhY3RpdmVQYXRocyI6eyJwYXRocyI6eyJwYXNzd29yZCI6ImluaXQiLCJ1c2VybmFtZSI6ImluaXQiLCJoZWFkZXIiOiJpbml0IiwicXEiOiJpbml0Iiwid2VpeGluIjoiaW5pdCIsImVtYWlsIjoiaW5pdCIsInFpYW5iYW8iOiJpbml0IiwiemlqaW5QYXkiOiJpbml0IiwiemhpZnVQYXkiOiJpbml0IiwiaXNfYWRtaW4iOiJpbml0Iiwicm9sZXR5cGUiOiJpbml0IiwiaWRjYXJkIjoiaW5pdCIsIm5hbWUiOiJpbml0IiwiYmFua2NvZGUiOiJkZWZhdWx0IiwiYmFua2NvdW50IjoiZGVmYXVsdCIsImJhbmtzIjoiaW5pdCIsImFkZHJlc3MiOiJpbml0IiwidXBkYXRlZCI6ImluaXQiLCJjcmVhdGVkIjoiaW5pdCIsIl9pZCI6ImluaXQifSwic3RhdGVzIjp7Imlnbm9yZSI6e30sImRlZmF1bHQiOnsiYmFua2NvZGUiOnRydWUsImJhbmtjb3VudCI6dHJ1ZX0sImluaXQiOnsiYmFua3MiOnRydWUsImFkZHJlc3MiOnRydWUsImhlYWRlciI6dHJ1ZSwicXEiOnRydWUsIndlaXhpbiI6dHJ1ZSwiZW1haWwiOnRydWUsInFpYW5iYW8iOnRydWUsInppamluUGF5Ijp0cnVlLCJ6aGlmdVBheSI6dHJ1ZSwiaXNfYWRtaW4iOnRydWUsInJvbGV0eXBlIjp0cnVlLCJpZGNhcmQiOnRydWUsIm5hbWUiOnRydWUsInVwZGF0ZWQiOnRydWUsImNyZWF0ZWQiOnRydWUsInVzZXJuYW1lIjp0cnVlLCJwYXNzd29yZCI6dHJ1ZSwiX2lkIjp0cnVlfSwibW9kaWZ5Ijp7fSwicmVxdWlyZSI6e319LCJzdGF0ZU5hbWVzIjpbInJlcXVpcmUiLCJtb2RpZnkiLCJpbml0IiwiZGVmYXVsdCIsImlnbm9yZSJdfSwicGF0aHNUb1Njb3BlcyI6e30sImVtaXR0ZXIiOnsiZG9tYWluIjpudWxsLCJfZXZlbnRzIjp7fSwiX2V2ZW50c0NvdW50IjowLCJfbWF4TGlzdGVuZXJzIjowfSwiJG9wdGlvbnMiOnRydWV9LCJpc05ldyI6ZmFsc2UsIl9kb2MiOnsiaGVhZGVyIjoiIiwicXEiOiIiLCJ3ZWl4aW4iOiIiLCJlbWFpbCI6IiIsInFpYW5iYW8iOjY4NDU3LCJ6aWppblBheSI6IiIsInpoaWZ1UGF5IjoiOTMyNzllMzMwOGJkYmJlZWQ5NDZmYzk2NTAxN2Y2N2EiLCJpc19hZG1pbiI6ZmFsc2UsInJvbGV0eXBlIjoiMCIsImlkY2FyZCI6IjYyMjQyNTE5OTMwODI1NzMyOSIsIm5hbWUiOiLms77nlKrot68iLCJiYW5rY29kZSI6IiIsImJhbmtjb3VudCI6IiIsImJhbmtzIjpbeyJpZCI6IjIwMTgwNTIyMTQyNjU2IiwibmFtZSI6IuS4reWbveW3peWVhumTtuihjOS4iua1t-mHkeadqOaUr-ihjCIsImNvdW50IjoiNjIyMjA4MTAwMTAxMTc4ODc1MyJ9LHsiaWQiOiIyMDE4MDUyMjE0MjY1NiIsIm5hbWUiOiLkuK3lm73pk7booYzkuIrmtbfph5HmnajmlK_ooYwiLCJjb3VudCI6IjYyMjIwODEwMDEwMTE3ODg3NTMifV0sImFkZHJlc3MiOlt7ImlkIjoiMjAxODA2MTMxNDQ3MTEiLCJ0eXBlIjoiNTU1IiwiYWRkcmVzcyI6ImdnZyJ9LHsiaWQiOiIyMDE4MDYxMzE0NTAyMSIsInR5cGUiOiIyMjIiLCJhZGRyZXNzIjoiMjIyIn0seyJpZCI6IjIwMTgwNjEzMTQ1NDA0IiwidHlwZSI6IjIyMjIiLCJhZGRyZXNzIjoiMzMzMzMifV0sInVwZGF0ZWQiOiIyMDE4LTA0LTA0VDA3OjMyOjE5LjYwNFoiLCJjcmVhdGVkIjoiMjAxOC0wNC0wNFQwNzozMjoxOS42MDRaIiwibW9iU3RhdHVzIjoiMCIsInVzZXJuYW1lIjoiMTc4NTg5Njg2NTciLCJwYXNzd29yZCI6Ijk2ZTc5MjE4OTY1ZWI3MmM5MmE1NDlkZDVhMzMwMTEyIiwiX2lkIjoiNWFjNDdmODM2MDhiMWQxOTgwOTA1YzhlIn0sIiRpbml0Ijp0cnVlLCJpYXQiOjE1Mjg5NTU3ODQsImV4cCI6MTUyODk1OTM4NH0.jQNDHLtqJeQWVeD70z2fuDl_zTffBSvxMRVZv0960DI
     */

    private int status;
    private String msg;
    private BodyBean body;
    private String token;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public BodyBean getBody() {
        return body;
    }

    public void setBody(BodyBean body) {
        this.body = body;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class BodyBean {
        /**
         * _id : 5ac47f83608b1d1980905c8e
         * password : 96e79218965eb72c92a549dd5a330112
         * username : 17858968657
         * mobStatus : 0
         * created : 2018-04-04T07:32:19.604Z
         * updated : 2018-04-04T07:32:19.604Z
         * address : [{"address":"ggg","type":"555","id":"20180613144711"},{"address":"222","type":"222","id":"20180613145021"},{"address":"33333","type":"2222","id":"20180613145404"}]
         * banks : [{"count":"6222081001011788753","name":"中国工商银行上海金杨支行","id":"20180522142656"},{"count":"6222081001011788753","name":"中国银行上海金杨支行","id":"20180522142656"}]
         * bankcount :
         * bankcode :
         * name : 泾甪路
         * idcard : 622425199308257329
         * roletype : 0
         * is_admin : false
         * zhifuPay : 93279e3308bdbbeed946fc965017f67a
         * zijinPay :
         * qianbao : 68457
         * email :
         * weixin :
         * qq :
         * header :
         */

        private String _id;
        private String password;
        private String username;
        private String mobStatus;
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
        private Double qianbao;
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

        public Double getQianbao() {
            return qianbao;
        }

        public void setQianbao(Double qianbao) {
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
             * address : ggg
             * type : 555
             * id : 20180613144711
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
