package com.example.wickettest.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("UserMaker")
public class UserMakerPage extends WebPage{
    public UserMakerPage() {
        var toHomeLink = new BookmarkablePageLink("toHome", HomePage.class);
        add(toHomeLink);
    }
}
