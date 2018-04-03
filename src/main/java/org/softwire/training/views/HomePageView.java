package org.softwire.training.views;

import io.dropwizard.views.View;
import org.softwire.training.models.User;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class HomePageView extends View {
    private User loggedInUser;
    private final List<User> users;

    public HomePageView(User user, List<User> users) {
        super("HomePageView.mustache", StandardCharsets.UTF_8);
        this.loggedInUser = user;
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
