package io.ionic.ylnewapp.bean.market;

import java.util.List;

/**
 * Created by mogojing on 2018/6/26/0026.
 */

public class SeachGrid {

    /**
     * success : true
     * data : [{"value":"BTC","sort":1},{"value":"ETH","sort":2},{"value":"ADA","sort":3},{"value":"LTC","sort":4},{"value":"NEO","sort":5}]
     * message : 处理成功！
     */

    private boolean success;
    private String message;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * value : BTC
         * sort : 1
         */

        private String value;
        private int sort;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
