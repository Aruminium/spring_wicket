package com.example.wickettest.repository;

import com.example.wickettest.data.AuthUser;

import java.util.List;

public interface IAuthUserRepository {
    /**
     * ユーザー名とパスワードをAuthUserテーブルに記録する
     *
     * @param userName ユーザー名
     * @param userPass パスワード
     * @return データベースの更新行数
     */
    public int insert(String userName, String userPass);

    /**
     * ユーザ名とパスワードが一致するレコードがAuthUserテーブルにあるか検索する
     *
     * @param userName ユーザ名
     * @param userPass パスワード
     * @return レコードの有無, 存在すれば<code>true</code>, それ以外は<code>false</code>
     */
    public boolean exists(String userName, String userPass);

    /**
     * AuthUserテーブルの全ての情報を検索する
     *
     * @return レコードの内容を {@link AuthUser} の {@link List} で返す
     */
    public List<AuthUser> find();

    /**
     * userNameを新しくする
     * @param newUserName 新しいユーザ名
     * @param userName 現在のユーザ名
     *
     * @return ユーザ名を更新
     */
    public void changeUserName(String newUserName, String userName);
}
