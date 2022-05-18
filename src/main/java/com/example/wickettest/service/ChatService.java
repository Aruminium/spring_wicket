package com.example.wickettest.service;

import com.example.wickettest.data.ChatData;
import com.example.wickettest.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService implements IChatService {
    private IChatRepository chatDataRepos;

    @Autowired
    public ChatService(IChatRepository chatDataRepos){
        this.chatDataRepos = chatDataRepos;
    }

    @Override
    public String makeCurrentMMDDMS(){
        var now = LocalDateTime.now();
        var str = now.getMonthValue() + "月"
                + now.getDayOfMonth() + "日"
                + now.getHour()
                + "時" + now.getMinute() + "分";
        return str;
    }

    @Override
    public void registerChat(String userName, String msgTime, String msgBody){
        int n = chatDataRepos.insert(userName, msgTime, msgBody);
        System.out.println("記録行数:" + n);
    }

    @Override
    public List<ChatData> findChatDates(){
        var chatData = chatDataRepos.find();
        System.out.println("データ件数:" + chatData.size());
        return chatData;
    }

    @Override
    public void changeUserName(String newUserName, String userName){
        chatDataRepos.changeUserName(newUserName, userName);
    }

    @Override
    public List<ChatData> findUserChatDates(String userName){
        List<ChatData> chatData = chatDataRepos.findUserChat(userName);
        return chatData;
    }

}
