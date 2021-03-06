package io.ionic.ylnewapp.bean;

import java.util.List;

/**
 * Created by mogojing on 2018/5/31/0031.
 */

public class AddressBean {

    /**
     * status : 200
     * body : [{"id":"20180514154108","type":1,"address":"11111111111111111111111"},{"id":"20180514154108","type":2,"address":"5555555"}]
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
         * id : 20180514154108
         * type : 1
         * address : 11111111111111111111111
         */

        private String id;
        private String type;
        private String address;

        private Boolean defult;

        public Boolean getDefult() {
            return defult;
        }

        public void setDefult(Boolean defult) {
            this.defult = defult;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
