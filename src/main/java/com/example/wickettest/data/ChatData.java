package com.example.wickettest.data;

public class ChatData {
    private final String userName;
    private final String msgTime;
    private final String msg;

    public ChatData(String userName, String msgTime, String msg){
        this.userName = userName;
        this.msgTime = msgTime;
        this.msg = msg;
    }


    public String getUserName() {
        return userName;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public String getMsg() {
        return msg;
    }
}
