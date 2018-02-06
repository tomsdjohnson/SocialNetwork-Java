package org.softwire.training.views;

import io.dropwizard.views.View;
import org.softwire.training.models.UserSummary;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class HomePageView extends View {
    private UserSummary loggedInUser;
    private final List<UserSummary> users;

    public HomePageView(UserSummary user, List<UserSummary> users) {
        super("HomePageView.ftl", StandardCharsets.UTF_8);
        this.loggedInUser = user;
        this.users = users;
    }

    public List<UserSummary> getUsers() {
        return users;
    }

    public UserSummary getLoggedInUser() {
        return loggedInUser;
    }
}
