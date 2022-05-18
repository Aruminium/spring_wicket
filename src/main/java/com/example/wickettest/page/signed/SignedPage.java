package com.example.wickettest.page.signed;

import com.example.wickettest.MySession;
import com.example.wickettest.data.AuthUser;
import com.example.wickettest.page.UserMakerPage;
import com.example.wickettest.service.IUserService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;
import com.example.wickettest.page.SignPage;

import java.awt.print.Book;
import java.util.List;

@AuthorizeInstantiation(Roles.USER)
@MountPath("Signed")
public class SignedPage extends WebPage {

    @SpringBean
    private IUserService userService;

    public SignedPage() {
        var signedUserName = MySession.get().getUserName();
        var name = Model.of(signedUserName);
        var userNameLabel = new Label("userName", name);
        add(userNameLabel);

        var signoutLink = new Link<Void>("signout") {
            @Override
            public void onClick() {
                MySession.get().invalidate();
                setResponsePage(SignPage.class);
            }
        };
        add(signoutLink);

        var chatServiceLink = new BookmarkablePageLink("chatService", ChatPage.class);
        add(chatServiceLink);

        var changeUserNameLink = new BookmarkablePageLink("changeUserName", ChangeUserNamePage.class);
        add(changeUserNameLink);

        var authUsersModel = Model.ofList(userService.findAuthUsers());

        var usersLV = new ListView<>("users", authUsersModel) {
            @Override
            protected void populateItem(ListItem<AuthUser> listItem) {
                var itemModel = listItem.getModel();
                var authUser = itemModel.getObject();

                var userNameModel = Model.of(authUser.getUserName());
                var userNameLabel = new Label("userName", userNameModel);
                listItem.add(userNameLabel);

                var userPassModel = Model.of(authUser.getUserPass());
                var userPassLabel = new Label("userPass", userPassModel);
                listItem.add(userPassLabel);
            }
        };
        add(usersLV);
    }
}
