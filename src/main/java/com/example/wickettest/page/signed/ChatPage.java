package com.example.wickettest.page.signed;


import com.example.wickettest.MySession;
import com.example.wickettest.data.AuthUser;
import com.example.wickettest.data.ChatData;
import com.example.wickettest.page.SignPage;
import com.example.wickettest.service.IChatService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.USER)
@MountPath("chat")
public class ChatPage extends WebPage {
    @SpringBean
    IChatService ChatService;

    public ChatPage(){
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

        var chatDataModel = Model.ofList(ChatService.findChatDates());

        var chatLV = new ListView<>("chatList", chatDataModel) {

            @Override
            protected void populateItem(ListItem<ChatData> listItem) {
                var itemModel = listItem.getModel();
                var authUser = itemModel.getObject();

//                var userNameModel = Model.of(authUser.getUserName());
//                var userNameLabel = new Label("userName", userNameModel);
//                listItem.add(userNameLabel);

            }
        };
        add(chatLV);
    }
}
