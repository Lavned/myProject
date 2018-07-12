package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/6/6/0006.
 */

public class AccountBean {


    /**
     * status : 200
     * body : {"username":"17858968657","qianbao":4722,"count":157446,"coins":[{"_id":"5ad076feeab9170a59be35cd","cmount":22999,"name":"BNCN"},{"_id":"5b18a890867e39069eaeb0a3","name":"BTC","cmount":1},{"_id":"5b18ac044a3ba11e2500f689","name":"ETH","cmount":0}]}
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
         * username : 17858968657
         * qianbao : 4722
         * count : 157446
         * coins : [{"_id":"5ad076feeab9170a59be35cd","cmount":22999,"name":"BNCN"},{"_id":"5b18a890867e39069eaeb0a3","name":"BTC","cmount":1},{"_id":"5b18ac044a3ba11e2500f689","name":"ETH","cmount":0}]
         */

        private String username;
        private Double qianbao;
        private Double count;
        private List<CoinsBean> coins;
        private int unPay;

        public void setUnPay(int unPay) {
            this.unPay = unPay;
        }

        public int getUnPay() {
            return unPay;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Double getQianbao() {
            return qianbao;
        }

        public void setQianbao(Double qianbao) {
            this.qianbao = qianbao;
        }

        public Double getCount() {
            return count;
        }

        public void setCount(Double count) {
            this.count = count;
        }

        public List<CoinsBean> getCoins() {
            return coins;
        }

        public void setCoins(List<CoinsBean> coins) {
            this.coins = coins;
        }

        public static class CoinsBean {
            /**
             * _id : 5ad076feeab9170a59be35cd
             * cmount : 22999
             * name : BNCN
             */

            private String _id;
            private int cmount;
            private String name;
            private String icon;

            public void setIcon(String icon) {
                this.icon = icon;
            }

            public String getIcon() {
                return icon;
            }

            public String get_id() {
                return _id;
            }

            public void set_id(String _id) {
                this._id = _id;
            }

            public int getCmount() {
                return cmount;
            }

            public void setCmount(int cmount) {
                this.cmount = cmount;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
