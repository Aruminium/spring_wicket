package com.example.wickettest;

import com.giffing.wicket.spring.boot.starter.app.WicketBootSecuredWebApplication;
import com.giffing.wicket.spring.boot.starter.app.WicketBootWebApplication;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WicketTestApplication extends WicketBootSecuredWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WicketTestApplication.class, args);
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass(){
        return MySession.class;
    }

}
