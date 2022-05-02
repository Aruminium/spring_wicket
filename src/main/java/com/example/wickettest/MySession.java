package com.example.wickettest;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

import javax.management.relation.Role;
import java.util.Objects;

public class MySession extends AbstractAuthenticatedWebSession{
    private String userName;

    public MySession(Request request){
        super(request);
        this.userName = null;
    }

    public void sign(String userName){
        replaceSession();
        this.userName = userName;
    }

    @Override
    public Roles getRoles(){
        if (isSignedIn()){
            return new Roles(Roles.USER);
        }
        return new Roles();
    }

    @Override
    public boolean isSignedIn() {
        return Objects.nonNull(this.userName);
    }

    public String getUserName(){
        return this.userName;
    }

    public static MySession get() {
        return (MySession) Session.get();
    }
}
