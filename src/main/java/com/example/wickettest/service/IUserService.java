package com.example.wickettest.service;

import com.example.wickettest.data.AuthUser;

import java.util.List;

public interface IUserService {
    public void registerUser(String userName, String userPass);

    /**
     * ユーザ名とパスワードをデータベースに照合する
     *
     * @param userName ユーザ名
     * @param userPass パスワード
     * @return 照合成功であれば<code>true</code>, 照合失敗は<code>false</code>
     */
    public boolean existsUser(String userName, String userPass);

    /**
     * ユーザ名とパスワードの一覧、AuthUser型のリストで検索する
     *
     * @return AuthUser型のListインスタンス
     */
    public List<AuthUser> findAuthUsers();

    /**
     * userNameを新しくする
     * @param newUserName 新しいユーザ名
     * @param userName 現在のユーザ名
     *
     * @return ユーザ名を更新
     */
    public void changeUserName(String newUserName, String userName);
}
