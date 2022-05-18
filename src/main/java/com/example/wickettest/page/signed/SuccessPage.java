package com.example.wickettest.page.signed;

import com.example.wickettest.MySession;
import com.example.wickettest.page.HomePage;
import com.example.wickettest.page.SignPage;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.link.Link;
import org.wicketstuff.annotation.mount.MountPath;


@MountPath("Success")
@AuthorizeInstantiation(Roles.USER)
public class SuccessPage extends WebPage {
    public SuccessPage(){
        var signoutLink = new Link<Void>("signout") {
            @Override
            public void onClick() {
                MySession.get().invalidate();
                setResponsePage(SignPage.class);
            }
        };
        add(signoutLink);
    }
}
