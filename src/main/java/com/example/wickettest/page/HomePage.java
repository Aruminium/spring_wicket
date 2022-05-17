package com.example.wickettest.page;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import com.example.wickettest.service.ISampleService;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;

@WicketHomePage
@MountPath("Home")
public class HomePage extends WebPage{

    @SpringBean
    private ISampleService service;
    public HomePage(){
        var youModel = Model.of("Wicket-Spring-Boot");
        var gakusekiModel = Model.of("b2201680");
        var youLabel = new Label("you", youModel);
        var gakusekiLabel = new Label("gakuseki", gakusekiModel);
        var nameModel = Model.of("寺山碧威");
        var nameLabel = new Label("name", nameModel);
        var timeModel = Model.of(service.makeCurrentHMS());
        var timeLabel = new Label("time", timeModel);
        var randModel = Model.of(service.makeRandint());
        var randLabel = new Label("rand", randModel);

        var toUserMakerLink = new BookmarkablePageLink("toUserMaker", UserMakerPage.class);

        var toSignInPageLink = new BookmarkablePageLink("toSignIn", SignPage.class);
        add(toSignInPageLink);
        add(toUserMakerLink);
        add(timeLabel);
        add(nameLabel);
        add(gakusekiLabel);
        add(youLabel);
        add(randLabel);
    }
}
