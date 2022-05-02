package com.example.wickettest.page;

import com.example.wickettest.MySession;
import com.example.wickettest.page.signed.SignedPage;
import com.giffing.wicket.spring.boot.context.scan.WicketSignInPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Objects;

@WicketSignInPage
@MountPath("Sign")
public class SignPage extends WebPage{
    public SignPage(){
        var userNameModel = Model.of("");
        var userPassModel = Model.of("");

        var userInfoForm = new Form<>("userInfo"){
            @Override
            protected void onSubmit(){
                var userName = userNameModel.getObject();
                var userPass = userPassModel.getObject();
                if(Objects.equals(userName, "b2201680")
                && Objects.equals(userPass, "b2201680")){
                    MySession.get().sign(userName);
                }
                setResponsePage(new SignedPage());
            }
        };
        add(userInfoForm);

        var userNameField = new TextField<>("userName", userNameModel){
            @Override
            protected void onInitialize(){
                super.onInitialize();
                add(StringValidator.lengthBetween(8, 32));
            }
        };
        userInfoForm.add(userNameField);

        var userPassField = new PasswordTextField("userPass", userPassModel){
            @Override
            protected void onInitialize(){
                super.onInitialize();
                add(StringValidator.lengthBetween(8, 32));
            }
        };
        userInfoForm.add(userPassField);
    }
}
