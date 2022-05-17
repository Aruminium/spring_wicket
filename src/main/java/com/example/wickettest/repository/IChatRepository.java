package com.example.wickettest.repository;

import com.example.wickettest.data.AuthUser;
import com.example.wickettest.data.ChatData;
import java.util.List;

public interface IChatRepository {
    /**
     * ユーザ名と送信時間,メッセージをChatテーブルに記録する
     *
     * @param userName ユーザ名
     * @param msgTime 送信時間
     * @param msgBody メッセージ
     * @return データベースの更新行数
     */
    public int insert(String userName, String msgTime, String msgBody);

    /**
     * ユーザ名がレコードにあるかChatテーブルを検索する
     *
     * @param userName ユーザ名
     * @return レコードの有無, 存在すれば<code>true</code>, それ以外は<code>false</code>
     */
    public boolean exists(String userName);

    /**
     * Chatテーブルの全ての情報を検索する
     *
     * @return レコードの内容を {@link ChatData} の {@link List} で返す
     */
    public List<ChatData> find();
}
