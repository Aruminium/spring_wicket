package com.example.wickettest.page;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;

@WicketHomePage
@MountPath("Home")
public class HomePage extends WebPage{

    public HomePage(){
        var youModel = Model.of("Wicket-Spring-Boot");
        var gakusekiModel = Model.of("b2201680");
        var youLabel = new Label("you", youModel);
        var gakusekiLabel = new Label("gakuseki", gakusekiModel);
        var nameModel = Model.of("寺山碧威");
        var nameLabel = new Label("name", nameModel);
        add(nameLabel);
        add(gakusekiLabel);
        add(youLabel);
    }
}
