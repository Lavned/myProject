package io.ionic.ylnewapp.bean.response;

/**
 * Created by mogojing on 2018/6/6/0006.
 */

public class AccountBean {

    /**
     * status : 200
     * body : {"username":"17858968657","qianbao":98445,"count":59000}
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
         * qianbao : 98445
         * count : 59000
         */

        private String username;
        private int qianbao;
        private int count;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getQianbao() {
            return qianbao;
        }

        public void setQianbao(int qianbao) {
            this.qianbao = qianbao;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
