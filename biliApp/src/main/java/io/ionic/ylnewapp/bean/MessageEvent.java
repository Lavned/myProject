package io.ionic.ylnewapp.bean;

/**
 * Created by mogojing on 2018/6/20/0020.
 */

public class MessageEvent{
    private String message;
    public  MessageEvent(String message){
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}