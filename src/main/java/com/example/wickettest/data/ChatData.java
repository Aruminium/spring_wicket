package com.example.wickettest.data;

public class ChatData {
    private final String userName;
    private final String msgTime;
    private final String msgBody;

    public ChatData(String userName, String msgTime, String msgBody){
        this.userName = userName;
        this.msgTime = msgTime;
        this.msgBody = msgBody;
    }


    public String getUserName() {
        return userName;
    }

    public String getMsgTime() {
        return msgTime;
    }

    public String getMsgBody() {
        return msgBody;
    }
}
