package com.example.wickettest.repository;

import com.example.wickettest.data.ChatData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRepository implements IChatRepository{
    private final JdbcTemplate jdbc;

    @Autowired
    public ChatRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public int insert(String userName, String msgBody, String msgTime){
        var sql = "insert into chat_table values (?, ?, ?)";
        var n = jdbc.update(sql, userName, msgBody, msgTime);
        return n;
    }

    @Override
    public boolean exists(String userName) {
        var sql = "select true from chat_table "
                + "where user_name = ?";

        var booleans = jdbc.query(sql,
                SingleColumnRowMapper.newInstance(Boolean.class),
                userName);

        return !booleans.isEmpty();
    }

    @Override
    public List<ChatData> find() {
        String sql = "select user_name, msg_body, msg_time from chat_table";

        List<ChatData> chatData = jdbc.query(sql,
                DataClassRowMapper.newInstance(ChatData.class));

        return chatData;

    }
}
