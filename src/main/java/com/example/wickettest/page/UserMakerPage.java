package com.example.wickettest.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
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

        var userNameField = new TextField<>("userName", userNameModel);
        userInfoForm.add(userNameField);

        var userPassField = new PasswordTextField("userPass", userPassModel);
        userInfoForm.add(userPassField);
        add(userInfoForm);
    }
}
