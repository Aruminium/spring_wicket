package com.example.wickettest.service;

import com.example.wickettest.data.AuthUser;
import com.example.wickettest.repository.IAuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{
    private IAuthUserRepository authUserRepos;

    @Autowired
    public UserService(IAuthUserRepository authUserRepos){
        this.authUserRepos = authUserRepos;
    }

    @Override
    public void registerUser(String userName, String userPass){
        int n = authUserRepos.insert(userName, userPass);
        System.out.println("記録行数:" + n);
    }

    @Override
    public boolean existsUser(String userName, String userPass) {
        var result = authUserRepos.exists(userName, userPass);
        System.out.println(userName + ", " + userPass + " のユーザ照合結果:" + result);
        return result;
    }

    @Override
    public List<AuthUser> findAuthUsers() {
        var users = authUserRepos.find();
        System.out.println("データ件数:" + users.size());
        return users;
    }
}
