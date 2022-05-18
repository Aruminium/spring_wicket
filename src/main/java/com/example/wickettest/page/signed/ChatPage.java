package com.example.wickettest.page.signed;


import com.example.wickettest.MySession;
import com.example.wickettest.data.ChatData;
import com.example.wickettest.page.SignPage;
import com.example.wickettest.page.UserMakerCompPage;
import com.example.wickettest.page.UserMakerPage;
import com.example.wickettest.service.IChatService;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.USER)
@MountPath("chat")
public class ChatPage extends WebPage {

    @SpringBean
    IChatService chatService;

    public ChatPage(){

        var signedUserName = MySession.get().getUserName();
        var name = Model.of(signedUserName);
        var msgBodyModel = Model.of("");



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




        Form<Void> chatForm = new Form<Void>("newChat"){
            @Override
            protected void onSubmit(){
                var timeModel = Model.of(chatService.makeCurrentMMDDMS());
                String userName = name.getObject();
                String msgBody = msgBodyModel.getObject();
                String time = timeModel.getObject();
                String msg = "送信データ:"
                        + userName
                        + ","
                        + msgBody;
                System.out.println(msg);
                chatService.registerChat(userName, time, msgBody);
                setResponsePage(new ChatPage());

            }
        };
        add(chatForm);

        var msgBodyField = new TextField<>("msgBody", msgBodyModel){
            @Override
            protected void onInitialize(){
                super.onInitialize();
                var validator = StringValidator.lengthBetween(1, 64);
                add(validator);
            }
        };
        chatForm.add(msgBodyField);

        var chatListModel = Model.ofList(chatService.findChatDates());


        new AjaxLink<>("newChat") {
            @Override
            public void onClick(AjaxRequestTarget target){
                var chatLV = new ListView<>("chatList", chatListModel) {
                    @Override
                    protected void populateItem(ListItem<ChatData> listItem) {
                        var itemModel = listItem.getModel();
                        var chatData = itemModel.getObject();

                        var userNameModel = Model.of(chatData.getUserName());
                        var userNameLabel = new Label("toUserName", userNameModel);
                        listItem.add(userNameLabel);
                        var toUserMakerLink = new BookmarkablePageLink("toUserName", userChatData.class);
                        add(toUserMakerLink);

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
        };
        var chatLV = new ListView<>("chatList", chatListModel) {
            @Override
            protected void populateItem(ListItem<ChatData> listItem) {

                var itemModel = listItem.getModel();
                var chatData = itemModel.getObject();

                var userNameModel = Model.of(chatData.getUserName());
                var userNameLabel = new Label("toUserName", userNameModel);
                listItem.add(userNameLabel);
//                        setResponsePage(new userChatData(userNameModel));

                var msgTimeModel = Model.of(chatData.getMsgTime());
                var msgTimeLabel = new Label("msgTime", msgTimeModel);
                listItem.add(msgTimeLabel);

                var msgBodyModel = Model.of(chatData.getMsgBody());
                var msgBodyLabel = new Label("msgBody", msgBodyModel);
                listItem.add(msgBodyLabel);

                new AjaxLink<>("toUserName"){
                    @Override
                    public void onClick(AjaxRequestTarget target){
                        setResponsePage(new userChatData(userNameModel));
                    }
                };
            }
        };
        add(chatLV);
    }
}
