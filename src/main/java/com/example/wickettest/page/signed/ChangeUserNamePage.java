package com.example.wickettest.page.signed;

import com.example.wickettest.MySession;
import com.example.wickettest.page.SignPage;
import com.example.wickettest.page.UserMakerCompPage;
import com.example.wickettest.service.IChatService;
import com.example.wickettest.service.IUserService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.springframework.context.annotation.Role;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.USER)
@MountPath("userChangeName")
public class ChangeUserNamePage extends WebPage {

    @SpringBean
    private IUserService userService;

    @SpringBean
    private IChatService chatService;

    public ChangeUserNamePage(){
        var signedUserName = MySession.get().getUserName();
        var name = Model.of(signedUserName);
        var userNameLabel = new Label("userName", name);
        add(userNameLabel);

        var newUserNameModel = Model.of("");

        Form<Void> newUserNameForm = new Form<Void>("changeUserName"){
            @Override
            protected void onSubmit(){
                String newUserName = newUserNameModel.getObject();
                String userName = name.getObject();
                String msg = "新しいユーザ名:"
                        + newUserName;
                System.out.println(msg);
                chatService.changeUserName(newUserName, userName);
                userService.changeUserName(newUserName, userName);
                setResponsePage(new SuccessPage());
            }
        };
        add(newUserNameForm);

        var newUserNameField = new TextField<>("newUserName", newUserNameModel){
            @Override
            protected void onInitialize(){
                super.onInitialize();
                var validator = StringValidator.lengthBetween(8, 32);
                add(validator);
            }
        };
        newUserNameForm.add(newUserNameField);
    }
}
