package com.example.wickettest.service;

import com.example.wickettest.data.ChatData;
import com.example.wickettest.repository.IAuthUserRepository;
import com.example.wickettest.repository.IChatRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService implements IChatService{
    private IChatRepository chatDataRepos;

    @Override
    public String makeCurrentHMS(){
        var now = LocalDateTime.now();
        var str = now.getHour()
                + ":" + now.getMinute()
                + ":" + now.getSecond();
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

}
