package com.example.wickettest.service;

import com.example.wickettest.data.ChatData;

import java.util.List;

public interface IChatService {
    /**
     * @return 現在の時:分:秒を文字列で返す
     */
    public String makeCurrentHMS();

    /**
     * データベースに以下の要素を登録
     *
     * @param userName ユーザ名
     * @param msgTime 投稿時間
     * @param msgBody メッセージ
     */
    public void registerChat(String userName, String msgTime, String msgBody);

    /**
     * ユーザ名とパスワードの一覧、AuthUser型のリストで検索する
     *
     * @return AuthUser型のListインスタンス
     */
    public List<ChatData> findChatDates();
}
