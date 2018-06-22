package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/6/6/0006.
 */

public class AccountBean {


    /**
     * status : 200
     * body : {"username":"15821094419","qianbao":1690,"count":10240551,"size":[{"status":0,"size":41},{"status":1,"size":10},{"status":2,"size":3},{"status":3,"size":1}]}
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
         * username : 15821094419
         * qianbao : 1690
         * count : 10240551
         * size : [{"status":0,"size":41},{"status":1,"size":10},{"status":2,"size":3},{"status":3,"size":1}]
         */

        private String username;
        private int qianbao;
        private int count;
        private List<SizeBean> size;

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

        public List<SizeBean> getSize() {
            return size;
        }

        public void setSize(List<SizeBean> size) {
            this.size = size;
        }

        public static class SizeBean {
            /**
             * status : 0
             * size : 41
             */

            private int status;
            private int size;

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }
        }
    }
}
