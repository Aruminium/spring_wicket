package com.example.wickettest.service;

import com.example.wickettest.data.ChatData;

import java.util.List;

public interface IChatService {
    /**
     * @return 現在の時:分:秒を文字列で返す
     */
    public String makeCurrentMMDDMS();

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

    /**
     * userNameを新しくする
     * @param newUserName 新しいユーザ名
     * @param userName 現在のユーザ名
     *
     * @return ユーザ名を更新
     */
    public void changeUserName(String newUserName, String userName);

    /**
     * 指定したユーザのチャットリストを返す
     * @param userName　ユーザ名
     *
     * @return List<ChatData>
     */
    public List<ChatData> findUserChatDates(String userName);
}
