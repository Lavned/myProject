package io.ionic.ylnewapp.bean;

/**
 * Created by mogojing on 2018/6/7/0007.
 */

public class CommRespBean {

    /**
     * status : 401
     * msg : 用户未登录
     */

    private int status;
    private String msg;

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
}
