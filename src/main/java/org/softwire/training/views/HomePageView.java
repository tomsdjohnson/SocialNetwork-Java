package org.softwire.training.views;

import io.dropwizard.views.View;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class HomePageView extends View {
    private String loggedInUser;
    private final List<String> users;

    public HomePageView(String user, List<String> users) {
        super("HomePageView.ftl", StandardCharsets.UTF_8);
        this.loggedInUser = user;
        this.users = users;
    }

    public List<String> getUsers() {
        return users;
    }

    public String getLoggedInUser() {
        return loggedInUser;
    }
}
