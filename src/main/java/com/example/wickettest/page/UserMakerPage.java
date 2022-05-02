package com.example.wickettest.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.example.wickettest.service.IUserService;

@MountPath("UserMaker")
public class UserMakerPage extends WebPage{

    @SpringBean
    private IUserService userService;

    public UserMakerPage() {
        var userNameModel = Model.of("");
        var userPassModel = Model.of("");

        var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        add(toHomeLink);

        Form<Void> userInfoForm = new Form<Void>("userInfo"){
            @Override
            protected void onSubmit(){
                String userName = userNameModel.getObject();
                String userPass = userPassModel.getObject();
                String msg = "送信データ:"
                        + userName
                        + ","
                        + userPass;
                System.out.println(msg);
                userService.registerUser(userName, userPass);
                setResponsePage(new UserMakerCompPage(userNameModel));
            }
        };
        add(userInfoForm);

        var userNameField = new TextField<>("userName", userNameModel){
            @Override
            protected void onInitialize(){
                super.onInitialize();
                var validator = StringValidator.lengthBetween(8, 32);
                add(validator);
            }
        };
        userInfoForm.add(userNameField);

        var userPassField = new PasswordTextField("userPass", userPassModel){
            @Override
            protected  void onInitialize(){
                super.onInitialize();
                var validator = StringValidator.lengthBetween(8, 32);
                add(validator);
            }
        };
        userInfoForm.add(userPassField);
        add(userInfoForm);
    }
}
