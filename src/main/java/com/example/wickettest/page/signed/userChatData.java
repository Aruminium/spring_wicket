package com.example.wickettest.page.signed;

import com.example.wickettest.MySession;
import com.example.wickettest.data.ChatData;
import com.example.wickettest.page.SignPage;
import com.example.wickettest.service.IChatService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("userChatTable")
@AuthorizeInstantiation(Roles.USER)
public class userChatData extends WebPage {

    @SpringBean
    IChatService chatService;

    public userChatData(IModel<String> userNameModel){
        var nameLabel = new Label("userName", userNameModel);
        add(nameLabel);

        var signoutLink = new Link<Void>("signout") {
            @Override
            public void onClick() {
                MySession.get().invalidate();
                setResponsePage(SignPage.class);
            }
        };
        add(signoutLink);

        var signedPageLink = new BookmarkablePageLink("toBack", SignedPage.class);
        add(signedPageLink);

        var chatListModel = Model.ofList(chatService.findUserChatDates(userNameModel.getObject()));

        var chatLV = new ListView<>("chatList", chatListModel) {
            @Override
            protected void populateItem(ListItem<ChatData> listItem) {
                var itemModel = listItem.getModel();
                var chatData = itemModel.getObject();

                var userNameModel = Model.of(chatData.getUserName());
                var userNameLabel = new Label("userName", userNameModel);
                listItem.add(userNameLabel);

                var msgTimeModel = Model.of(chatData.getMsgTime());
                var msgTimeLabel = new Label("msgTime", msgTimeModel);
                listItem.add(msgTimeLabel);

                var msgBodyModel = Model.of(chatData.getMsgBody());
                var msgBodyLabel = new Label("msgBody", msgBodyModel);
                listItem.add(msgBodyLabel);
            }
        };
        add(chatLV);
    }
}
